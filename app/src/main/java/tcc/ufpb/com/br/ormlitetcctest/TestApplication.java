package tcc.ufpb.com.br.ormlitetcctest;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Deyvison on 28/01/2017.
 */
public class TestApplication extends Application {

    private DBManager db;

    // criar uma lista para contextos default
    // verificar se o contexto já está no banco (através do nome)
    // adicionar o contexto default que não estiver no banco

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
//        db.insertPalavraFacil("Animais",new Palavra("pato","path pato",true));

        // antes de inserir, fazer uma pesquisa em contexto/palavras pra ver se já existem

        
        db.selectPalavraFacil("Animais");
        ArrayList<Contexto> retorno = db.getContextos();
        Log.i("lol", retorno.size()+"");
        for(Contexto c : retorno){
            Log.i("lol",c.getNome()+" "+c.getPathImagem()+" "+c.isDefault());
        }
//
//
    }
}
