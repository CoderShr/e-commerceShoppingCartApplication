//package com.cart.ShoppingService.Model;
//
//@Entity
//@Table(name = "tokens")
//public class AuthenticationToken {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
//
//
//    private String token;
//
//    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
//    @JoinColumn(nullable = false, name = "user_id")
//    private User user;
//
//    public AuthenticationToken(User user) {
//        this.user = user;
//        this.token = UUID.randomUUID().toString();
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getToken() {
//        return token;
//    }
//
//    public void setToken(String Token) {
//        this.token = Token;
//    }
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public AuthenticationToken(Integer id, String Token, User user) {
//        this.id = id;
//        this.token = Token;
//        this.user = user;
//    }
//
//    public AuthenticationToken() {
//    }
//}
