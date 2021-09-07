package ua.hehmde.kvjewelry.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@NamedEntityGraph(
        name = "product-metals-stones",
        attributeNodes = {
                @NamedAttributeNode("metals"),
                @NamedAttributeNode("stones")
        }
)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "vendorcode")
    private Long vendorcode;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", columnDefinition = "TIMESTAMP default CURRENT_TIMESTAMP", nullable = false)
    private Date created;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "name_ru")
    private String name_ru;

    @Column(name = "name_ua")
    private String name_ua;

    @Column(name = "image")
    private String image;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "stock")
    private Integer stock;

    @JoinTable
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Metal> metals;

    @JoinTable
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Stone> stones;
}
