import Comandos.Categoria.ComandoGetCategoria;
import Comandos.ComandoFactory;
import org.junit.Assert;
import org.junit.Test;

public class ComandoCategoria_Test {

    @Test
    public void getCategoriaTest() throws Exception {
        ComandoGetCategoria comandoGetCategoria = ComandoFactory.comandoGetCategoriaInstancia(1);
        Assert.assertNotNull(comandoGetCategoria.getResult());
    }

}
