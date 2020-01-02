package br.com.minhascontas.util;

import br.com.minhascontas.domain.exception.MinhasContasException;
import org.springframework.http.HttpStatus;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 *
 * Classe com métodos auxiliares para a aplicação em geral
 *
 * @author raphael.moreira
 * @version 1.0.0
 * @since 06/11/2019
 */
public class Util {

    public static String getMessageValidation(String chave, Object... params) {
        return getMessageFile(chave, params, "messages_validation");
    }

    public static String getMessageApplication(String chave, Object... params) {
        return getMessageFile(chave, params, "messages_application");
    }

    private static String getMessageFile(String chave, Object[] params, String nomeArquivo) {

        ResourceBundle rb = ResourceBundle.getBundle(nomeArquivo, new UTF8Control());

        if (rb.containsKey(chave)) {
            if (params.length > 0) {
                return MessageFormat.format(rb.getString(chave), params);
            } else {
                return rb.getString(chave);
            }
        }
        return null;
    }

    public static Locale getLocale() {
        return new Locale("pt", "BR");
    }

    static class UTF8Control extends ResourceBundle.Control {

        public ResourceBundle newBundle(String baseName, Locale locale, String format, ClassLoader loader, boolean reload) {

            String bundleName = toBundleName(baseName, getLocale());
            String resourceName = toResourceName(bundleName, "properties");
            ResourceBundle bundle = null;

            try (InputStream stream = loader.getResourceAsStream(resourceName);) {
                if (stream != null) {
                    bundle = new PropertyResourceBundle(new InputStreamReader(stream, StandardCharsets.UTF_8));
                }
            } catch (Exception e) {
                throw new MinhasContasException("Arquivo não encontrado!", HttpStatus.NOT_ACCEPTABLE);
            }
            return bundle;
        }
    }
}
