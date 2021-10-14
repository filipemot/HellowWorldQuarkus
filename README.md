
# Hello World com Quarkus

**Gerar projeto**

[](https://code.quarkus.io/?g=br.filipemot&a=hello-world-quakus)[https://code.quarkus.io/?g=br.filipemot&a=hello-world-quakus](https://code.quarkus.io/?g=br.filipemot&a=hello-world-quakus)

**Extensão**

RestEasy

**Executar**

./mvnw compile quarkus:dev

**Chamando API**

[](http://localhost:8080/hello)[http://localhost:8080/hello](http://localhost:8080/hello)

**Adicionando Extensão do Hibernate Panache**

./mvnw quarkus:add-extensions -Dextensions="hibernate-orm-panache"

**Adicionando Extensão do Postgres**

./mvnw quarkus:add-extensions -Dextensions="quarkus-jdbc-postgresql"

**Adicionando Extensão do Jackson para retorno em JSON**

./mvnw quarkus:add-extensions -Dextensions="quarkus-resteasy-jackson"

**Adicionando Extensão do Jacoco para adicionar report de cobertura de código**

./mvnw quarkus:add-extensions -Dextensions="jacoco"

**Criando Entidade**

```java
package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Fruta extends PanacheEntity {
    public String nome;
		public int qtd;
}


```

**Criando DTO**

```java
package org.acme.dto;

public class FrutasDTO {

    private String nome;
    private int qtd;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }
}

```

**Criando Service**

```java

package org.acme.service;

import io.quarkus.arc.Lock;
import org.acme.dto.FrutasDTO;
import org.acme.model.Fruta;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Lock // Não chama os metodos paralelamente
@ApplicationScoped
public final class FrutasService {

    @Lock(value = Lock.Type.READ, time = 3, unit = TimeUnit.SECONDS)
    public List<Fruta> list() {
        return Fruta.listAll();
    }

    @Transactional
    public void create(FrutasDTO frutasDTO) {
        Fruta fruta = new Fruta();
        fruta.nome = frutasDTO.getNome();
        fruta.qtd = frutasDTO.getQtd();
        fruta.persist();
    }
}

```

**Criando API**

```java
package org.acme;

import org.acme.dto.FrutasDTO;
import org.acme.model.Fruta;
import org.acme.service.FrutasService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/frutas")
public class FrutasResource {

    @Inject
    FrutasService frutasService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Fruta> list() {
        return frutasService.list();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void create(FrutasDTO frutasDTO) {
        frutasService.create(frutasDTO);
    }
}

```

**Chamando API**

[](http://localhost:8080/frutas)[http://localhost:8080/frutas](http://localhost:8080/frutas)
