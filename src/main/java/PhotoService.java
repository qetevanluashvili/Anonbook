import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class PhotoService {
    private static final PhotoService instance = new PhotoService();
    private final EntityManagerFactory entityManagerFactory;

    private PhotoService() {
        entityManagerFactory = Persistence.createEntityManagerFactory("unit");
    }

    public static PhotoService getInstance() {
        return instance;
    }

    public void savePhoto(byte[] photoData, String fileName) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();

            Photo photo = new Photo();
            photo.setData(photoData);
            photo.setFileName(fileName);

            entityManager.persist(photo);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public List<Photo> getAllPhotos() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Photo> criteriaQuery = criteriaBuilder.createQuery(Photo.class);
            Root<Photo> root = criteriaQuery.from(Photo.class);
            criteriaQuery.select(root);

            TypedQuery<Photo> typedQuery = entityManager.createQuery(criteriaQuery);
            return typedQuery.getResultList();
        } finally {
            entityManager.close();
        }
    }
}


