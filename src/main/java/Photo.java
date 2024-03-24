import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Photos")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @Lob
    @Column(name = "photo_data", nullable = false)
    private byte[] data;

    @Column(name = "file_name", nullable = false)
    private String fileName;

}