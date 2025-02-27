package com.ctw.workstation.team.boundary;

import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.TeamInputDTO;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import jakarta.ws.rs.core.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestHTTPEndpoint(TeamResource.class)
class TeamResourceTest {

    Long teamId;

    @Test
    @DisplayName("Fetching the Teams No Content")
    @Order(1)
    void fetch_all_teams_has_no_content() {
        given()
                .header("Content-Type", "application/json")
        .when()
                .get()
        .then()
                .assertThat()
                .statusCode(200)
                .body(Matchers.equalTo("[]"))
                .body("[0].id", Matchers.nullValue());
    }

    @Test
    @DisplayName("Post a team")
    @Order(2)
    void create_team() {
        TeamInputDTO teamDTO = new TeamInputDTO("Mariazinhas", "Objeto", "Louisiana");
        given()
                .contentType(ContentType.JSON)
                .body(teamDTO)
        .when()
                .post()
        .then()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", Matchers.equalTo("Mariazinhas"))
                .body("product", Matchers.equalTo("Objeto"))
                .body("defaultLocation", Matchers.equalTo("Louisiana"))
                .body("id", Matchers.notNullValue())
                .body("id", Matchers.isA(Number.class))
                .body("id", Matchers.greaterThan(0))
                .body("createdAt", Matchers.notNullValue())
                .body("modifiedAt", Matchers.notNullValue());
    }

    @Test
    @Order(3)
    @DisplayName("Post a team No name")
    void create_team_no_name() {
        given()
                .header("Content-Type", "application/json")
                .body("{\"product\": \"Objeto\",\"defaultLocation\": \"Louisiana\"}")
        .when()
                .post()
        .then()
                .assertThat()
                .statusCode(500)
                .contentType(ContentType.JSON)
                .header("content-type", Matchers.stringContainsInOrder("application/json"));

    }

    @Test
    @Order(4)
    @DisplayName("Post a team No Product")
    void create_team_no_product() {
        given()
                .header("Content-Type", "application/json")
                .body("{\"name\": \"Objeto\",\"defaultLocation\": \"Louisiana\"}")
                .when().post()
                .then()
                .statusCode(500)
                .contentType(ContentType.JSON)
                .header("content-type", Matchers.stringContainsInOrder("application/json"));

    }

    @Test
    @Order(5)
    @DisplayName("Post a team One too Many")
    void create_team_One_too_many() {
        ValidatableResponse response = given()
                .header("Content-Type", "application/json")
                .body("{\"name\": \"Luisas\",\"product\": \"Carro\",\"defaultLocation\": \"HoChiMin\", \"outro\": \"ok\"}")
                .when().post()
                .then()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .body("name", Matchers.equalTo("Luisas"))
                .body("product", Matchers.equalTo("Carro"))
                .body("defaultLocation", Matchers.equalTo("HoChiMin"))
                .body("outro", Matchers.nullValue())
                .body("id", Matchers.notNullValue())
                .body("id", Matchers.isA(Number.class))
                .body("id", Matchers.greaterThan(0))
                .body("createdAt", Matchers.notNullValue())
                .body("modifiedAt", Matchers.notNullValue())
                //.body("modifiedAt", Matchers.isA(LocalDateTime.class))
                //.header("content-length", Matchers.greaterThan(0))
                .header("content-type", Matchers.stringContainsInOrder("application/json"));

        //teamid = response.

    }

    @Test
    @Order(6)
    @DisplayName("Fetching the Teams Has Content")
    void fetch_all_teams_has_content() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get()
                .then()
                .statusCode(200)
                .extract().as(new TypeRef<List<Team>>() {
                }).stream().findFirst().get()
/*
                .body("size()", Matchers.greaterThan(0))
                .body("name", Matchers.equalTo("Mariazinhas"))
                .body("product", Matchers.equalTo("Objeto"))
                .body("[0].defaultLocation", Matchers.equalTo("Louisiana"))
                .body("[0].id", Matchers.notNullValue())
                .body("[0].id", Matchers.isA(Number.class))
                .body("[0].id", Matchers.greaterThan(0))
                .body("[0].createdAt", Matchers.notNullValue())
                .body("[0].modifiedAt", Matchers.notNullValue())
                .header("content-type", Matchers.stringContainsInOrder("application/json"))
                */;

    }

    @Test
    @Order(7)
    @DisplayName("Fetching the Team  by id")
    void fetch_team_by_id() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("{teamId}", teamId)
                .then()
                .statusCode(200)
                .body("size()", Matchers.greaterThan(0))
                .body("name", Matchers.equalTo("Luisas"))
                .body("product", Matchers.equalTo("Carro"))
                .body("defaultLocation", Matchers.equalTo("HoChiMin"))
                .body("id", Matchers.notNullValue())
                .body("id", Matchers.isA(Number.class))
                .body("id", Matchers.greaterThan(0))
                .body("createdAt", Matchers.notNullValue())
                .body("modifiedAt", Matchers.notNullValue())
                .header("content-type", Matchers.stringContainsInOrder("application/json"));;
    }

    @Test
    @Order(8)
    @DisplayName("Fetching team by id does not exist")
    void fetch_team_by_id_does_not_exist() {
        given()
                .header("Content-Type", "application/json")
                .when()
                .get("2")
                .then()
                .statusCode(500)
                .contentType(ContentType.JSON)
                .header("content-type", Matchers.stringContainsInOrder("application/json"));
    }


}