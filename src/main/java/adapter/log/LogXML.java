package adapter.log;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Operacao;

/**
 *
 * @author ruaney
 */
public class LogXML  {

    
    public void log(Operacao operation) throws IOException {
        String log = String.format("<log>\\n\\t<timestamp>%s</timestamp>\\n\\t<operation>%s</operation>\\n</log>\\n",
                LocalDateTime.now().toString(), operation.toString());
        saveToFile(log);
    }

    private void saveToFile(String log) throws IOException {
        try (FileWriter writer = new FileWriter("logoperacoes.xml", true)) {
            writer.write(log);
        } catch (IOException e) {
           throw new IOException("erro ao salvar arquivo");
        }
    }
}
