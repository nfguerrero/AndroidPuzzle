package cyPuzzle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
/**
 * 
 * CyPuzzle main application runner
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = {"cyPuzzle", "cyPuzzle.controller", "cyPuzzle.model", "cyPuzzle.repository", "webSocket"})
public class PuzzleSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(PuzzleSpringApplication.class, args);
	}

}
