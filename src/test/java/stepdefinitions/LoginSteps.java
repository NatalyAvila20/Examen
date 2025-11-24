package stepdefinitions;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import io.cucumber.java.After;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;

import java.sql.*;

public class LoginSteps {

    private WebDriver driver;

    @Before
    public void setup() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) driver.quit();
    }

    @Given("abro la pagina de login")
    public void abrirLogin() {
        driver.get("http://localhost:8082/CtaCorriente/login.jsp");
    }

    @When("ingreso un rut valido desde la base de datos")
    public void ingresoRutValidoBD() throws Exception {
        String rutBD = "";

        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/cta",
                "postgres",
                "admin"
        );

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT rut FROM usuario LIMIT 1"
        );
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) rutBD = rs.getString("rut");

        rs.close(); stmt.close(); conn.close();

        driver.findElement(By.name("rut")).sendKeys(rutBD);
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    @Then("el login debe ser exitoso")
    public void loginExitoso() {
        // REEMPLAZA POR LO QUE MUESTRE TU HOME
        boolean ok = driver.getPageSource().contains("Bienvenido");
        assert ok;
    }

    @When("ingreso un rut invalido")
    public void ingresoRutInvalido() {
        driver.findElement(By.name("rut")).sendKeys("11111111-1");
        driver.findElement(By.cssSelector("input[type='submit']")).click();
    }

    @Then("el sistema debe mostrar un mensaje de error")
    public void mensajeError() {
        boolean existe = driver.getPageSource().contains("Error");
        assert existe;
    }
}
