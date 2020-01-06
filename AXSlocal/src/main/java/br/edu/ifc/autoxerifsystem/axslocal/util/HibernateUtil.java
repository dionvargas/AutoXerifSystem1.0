package br.edu.ifc.autoxerifsystem.axslocal.util;

import br.edu.ifc.autoxerifsystem.axslocal.model.Acesso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Curso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Digital;
import br.edu.ifc.autoxerifsystem.axslocal.model.Permisao;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.TipoUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Turma;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 *
 * @author Dionathan Luan de Vargas
 * @since 07/09/2019
 *
 */
public class HibernateUtil {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {

                // Create registry builder
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                Map<String, String> settings = new HashMap<>();
                settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                settings.put("hibernate.connection.url", "jdbc:mysql://191.52.40.14:3306/autoxerifsystem?useTimezone=true&serverTimezone=UTC");
//                settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/autoxerifsystem?useTimezone=true&serverTimezone=UTC");
                settings.put("hibernate.connection.username", "root");
                settings.put("hibernate.connection.password", "gordoinutil");
                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.hbm2ddl.auto", "update");

                // Apply settings
                registryBuilder.applySettings(settings);

                // Create registry
                registry = registryBuilder.build();

                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(Curso.class);
                sources.addAnnotatedClass(Sala.class);
                sources.addAnnotatedClass(TipoUsuario.class);
                sources.addAnnotatedClass(Turma.class);
                sources.addAnnotatedClass(Usuario.class);
                sources.addAnnotatedClass(Permisao.class);
                sources.addAnnotatedClass(Digital.class);
                sources.addAnnotatedClass(Acesso.class);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
