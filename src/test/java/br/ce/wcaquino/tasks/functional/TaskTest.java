package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TaskTest {
	
	/*@Test
	public void tesdteAmbiente() {
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://www.google.com");
	}*/
	
	public WebDriver acessarAplicacao() {
		
		WebDriver driver = new ChromeDriver();
		driver.navigate().to("http://localhost:8001/tasks");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}
	
	
	
	@Test
	public void deveSalvarTarefaComSucesso() {

		WebDriver driver = acessarAplicacao();
		
		//clicar em ADD Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever descricao
		driver.findElement(By.id("task")).sendKeys("Teste Via Selenium");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2025");
		
		// clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		
		// validar mensagem de sucesso
		String mensagem = driver.findElement(By.id("message")).getText() ;
		Assert.assertEquals("Sucess!", mensagem);
		
		//fechar o  browser
		driver.quit();
	}
	
	@Test
	public void naodeveSalvarTarefaSemDescricao() {

		WebDriver driver = acessarAplicacao();
		try {
		//clicar em ADD Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever descricao
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2025");
		
		// clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		
		// validar mensagem de sucesso
		String mensagem = driver.findElement(By.id("message")).getText() ;
		Assert.assertEquals("Fill the task description", mensagem);
		} finally {
			
			//fechar o  browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naodeveSalvarTarefaSemData() {

		WebDriver driver = acessarAplicacao();
		try {
		//clicar em ADD Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever descricao
		driver.findElement(By.id("task")).sendKeys("Teste Via Selenium");
		
		// clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		
		// validar mensagem de sucesso
		String mensagem = driver.findElement(By.id("message")).getText() ;
		Assert.assertEquals("Fill the due date", mensagem);
		} finally {
			
			//fechar o  browser
			driver.quit();
		}
		
	}
	
	@Test
	public void naodeveSalvarTarefaComDataPassada() {

		WebDriver driver = acessarAplicacao();
		try {
		//clicar em ADD Todo
		driver.findElement(By.id("addTodo")).click();
		
		// escrever descricao
		driver.findElement(By.id("task")).sendKeys("Teste Via Selenium");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");
		
		// clicar em salvar
		driver.findElement(By.id("saveButton")).click();
		
		
		// validar mensagem de sucesso
		String mensagem = driver.findElement(By.id("message")).getText() ;
		Assert.assertEquals("Due date must not be in past", mensagem);
		} finally {
			
			//fechar o  browser
			driver.quit();
		}
		
	}
	

	


}
