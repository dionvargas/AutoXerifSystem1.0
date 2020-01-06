
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOAcesso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.DAOUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBAcesso;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBSala;
import br.edu.ifc.autoxerifsystem.axslocal.dao.hibernate.HBUsuario;
import br.edu.ifc.autoxerifsystem.axslocal.model.Acesso;
import br.edu.ifc.autoxerifsystem.axslocal.model.Sala;
import br.edu.ifc.autoxerifsystem.axslocal.model.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class teste {

    public static void main(String[] args) {

        Calendar agora = Calendar.getInstance();
        //Pega a hora e data atual
        Calendar date = Calendar.getInstance();
        date.set(Calendar.HOUR_OF_DAY, 14);
        date.set(Calendar.MINUTE, 30);

        System.out.println("#######################");
        System.out.println("Agora: " + agora);
        System.out.println("Data: " + date);
        System.out.println("Teste agora after date: " + agora.after(date));
        System.out.println("Teste agora before date: " + agora.before(date));
        System.out.println("Dia da semana: " + agora.get(Calendar.DAY_OF_WEEK));
        System.out.println("#######################");

        System.exit(0);
    }
}
