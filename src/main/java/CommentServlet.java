import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;


public class CommentServlet extends HttpServlet {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("unit");


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ObjectMapper mapper = new ObjectMapper();

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {

            CommentRequest commentRequest = mapper.readValue(request.getInputStream(), CommentRequest.class);


            if (commentRequest == null || commentRequest.getPostId() <= 0 || commentRequest.getUsername() == null || commentRequest.getContent() == null) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Comment comment = new Comment();
            comment.setContent(commentRequest.getContent());

            entityManager.getTransaction().begin();

            entityManager.persist(comment);

            entityManager.getTransaction().commit();

            CommentResponse commentResponse = new CommentResponse(comment.getCommentId(), comment.getPostId(), comment.getContent());
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_CREATED);
            mapper.writeValue(response.getOutputStream(), commentResponse);
        } catch (IOException e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }
}

