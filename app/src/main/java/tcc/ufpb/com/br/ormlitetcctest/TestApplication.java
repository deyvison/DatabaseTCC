package tcc.ufpb.com.br.ormlitetcctest;

import android.app.Application;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Deyvison on 28/01/2017.
 */
public class TestApplication extends Application {

    private DBManager db;

    // criar uma lista para contextos default
    // verificar se o contexto já está no banco (através do nome)
    // adicionar o contexto default que não estiver no banco


    // para inserir/remover um contexto/palavra consultar no banco antes pra ver se existe

    @Override
    public void onCreate() {
        super.onCreate();

        db = new DBManager(this);


        //db.addContexto(new Contexto("Frutas","path frutas",true));
        //db.addContexto(new Contexto("Animais","path animais",true));
        //db.addContexto(new Contexto("DAtas","path datas",false));
        //db.delContexto("kkk");

        //db.setContext("Animais",new Contexto("Animais","lol",false));

        //db.addContexto(new Contexto("kk","path teste",true));
//
//        db.insertPalavraFacil("Animais",new Palavra("Gato","path gato",true));
//        db.insertPalavraFacil("Animais",new Palavra("cachorro","path cachorro",true));
//        db.insertPalavraFacil("Animais",new Palavra("tigre","path tigre",true));
        db.insertPalavraFacil("teste",new Palavra("teste","path pato",true));

        // antes de inserir, fazer uma pesquisa em contexto/palavras pra ver se já existem

        
        ArrayList<Palavra> palavras = db.selectPalavraFacil("Animais");
        Log.i("lol","palavras faceis cadastradas em animais="+palavras.size());
        for(Palavra pa : palavras){
            Log.i("lol",pa.getNome());
        }
        Log.i("lol","end of for");

        ArrayList<Palavra> palavras2 = db.selectPalavraFacil("teste");
        Log.i("lol","palavras faceis cadastradas em teste="+palavras2.size());

        Log.i("lol","selectpalavrabynome = "+ db.selectPalavraEasyByName("Animais","Gato").getNome());
        db.deletePalavraEasy("Animais","pato");

        ArrayList<Palavra> lol = db.selectPalavraFacil("Animais");
        Log.i("lol","palavras faceis cadastradas em animais="+lol.size());

        ArrayList<Contexto> retorno = db.getContextos();
        Log.i("lol", retorno.size()+"");
        for(Contexto c : retorno){
            Log.i("lol",c.getNome()+" "+c.getPathImagem()+" "+c.isDefault());
        }
//
//
    }
}
