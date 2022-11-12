package com.epam.cinema.model.impl;

import lombok.*;

import javax.persistence.*;

/**
 * Class UserAccount stores the amount of prepaid money the user has in the system. The balance is analyzed during the booking procedure
 */
@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * the amount of prepared money
     */
    @NonNull
    private Double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserModel user;
}
