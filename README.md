
# Hello World com Quarkus

**Gerar projeto**

[https://code.quarkus.io/?g=br.filipemot&a=hello-world-quakus](https://code.quarkus.io/?g=br.filipemot&a=hello-world-quakus)

**Extensão**

RestEasy

**Executar**

./mvnw compile quarkus:dev

**Chamando API**

[http://localhost:8080/hello](http://localhost:8080/hello)

**Adicionando Extensão do Hibernate Panache**

./mvnw quarkus:add-extensions -Dextensions="hibernate-orm-panache"

**Adicionando Extensão do Postgres**

./mvnw quarkus:add-extensions -Dextensions="quarkus-jdbc-postgresql"

**Adicionando Extensão do Jackson para retorno em JSON**

./mvnw quarkus:add-extensions -Dextensions="quarkus-resteasy-jackson"

**Criando Entidade**

    package org.acme.model;  
      
    import io.quarkus.hibernate.orm.panache.PanacheEntity;  
      
    import javax.persistence.Entity;  
      
    @Entity  
    public class Fruta extends PanacheEntity {  
        public String nome;  
     public int qtd;  
    }


**Criando API**

    import org.acme.model.Fruta;  
      
    import javax.transaction.Transactional;  
    import javax.ws.rs.GET;  
    import javax.ws.rs.POST;  
    import javax.ws.rs.Path;  
    import javax.ws.rs.Produces;  
    import javax.ws.rs.core.MediaType;  
    import java.util.List;  
      
    @Path("/frutas")  
    public class FrutasResource {  
      
        @GET  
     @Produces(MediaType.APPLICATION_JSON)  
        public List<Fruta> list() {  
            return Fruta.listAll();  
      }  
      
        @POST  
     @Produces(MediaType.APPLICATION_JSON)  
        @Transactional  
      public void create() {  
            Fruta fruta = new Fruta();  
      fruta.nome = "Maça";  
      fruta.qtd = 5;  
      fruta.persist();  
      }  
    }

**Chamando API**

[http://localhost:8080/frutas](http://localhost:8080/frutas)




