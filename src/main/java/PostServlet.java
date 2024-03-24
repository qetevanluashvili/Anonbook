import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PostServlet extends HttpServlet {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            PostRequest postRequest = mapper.readValue(request.getInputStream(), PostRequest.class);

            if (postRequest == null || postRequest.getTitle() == null || postRequest.getContent() == null) {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Post post = new Post(postRequest.getTitle(), postRequest.getContent());

            entityManager.getTransaction().begin();


            entityManager.persist(post);

            entityManager.getTransaction().commit();

            PostResponse postResponse = new PostResponse(post.getPostId(), post.getTitle(), post.getContent());
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            mapper.writeValue(response.getOutputStream(), postResponse);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}

