package tcc.ufpb.com.br.ormlitetcctest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Deyvison on 28/01/2017.
 */
public class DBManager {

    private static DBHelper dbHelper = null;


    public DBManager(Context context){

        if(dbHelper == null){
            dbHelper = new DBHelper(context);
        }
    }

    public void addContexto(Contexto contexto){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", contexto.getNome());
        values.put("pathImagem", contexto.getPathImagem());
        values.put("isDefault", contexto.isDefault()+"");

        database.insert("contexto", null, values);

    }

    public ArrayList<Contexto> getContextos(){
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String sql = "SELECT * FROM contexto";

        Cursor cursor = database.rawQuery(sql,null);

        ArrayList<Contexto> contextos = null;
        if(cursor != null && cursor.moveToFirst()){
            contextos = new ArrayList<>();

            do{
                Contexto c = new Contexto(cursor.getString(0),cursor.getString(1),Boolean.parseBoolean(cursor.getString(2)));
                contextos.add(c);
            }while (cursor.moveToNext());
        }
        return contextos;
    }


    public Contexto selectContextoByNome(String nome){
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String where = "nome" + " = ?";
        String[] whereArgs = {nome};

        Cursor cursor = database.rawQuery("SELECT * FROM contexto WHERE nome=?", new String[] {nome + ""});

        if(cursor.getCount() > 0) {

            cursor.moveToFirst();
            Contexto c = new Contexto(cursor.getString(0),cursor.getString(1),Boolean.parseBoolean(cursor.getString(2)));
            return c;
        }else{
            return null;
        }

    }

    public void delContexto(String nome) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("contexto", "nome=?", new String[]{nome});
    }


    public void setContexto(String nome, Contexto contexto) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", contexto.getNome());
        values.put("pathImagem", contexto.getPathImagem());
        values.put("isDefault", contexto.isDefault()+"");

        database.delete("contexto", "nome=?", new String[]{nome});
        database.insert("contexto", null, values);
    }



    public void insertPalavraFacil(String foreignkey ,Palavra p){
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", p.getNome() );
        values.put("nomeContexto", foreignkey);
        values.put("pathImagem", p.getPathImagem());
        values.put("isDefault", p.isDefault()+"");

        database.insert("easy", null, values);
    }


    public ArrayList<Palavra> selectPalavraFacil(String foreignKey){ // retorna todas as palavras fáceis de um contexto
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        // pega todos os registros do banco
        String query = "select easy.nome, easy.nomeContexto, easy.pathImagem, easy.isDefault " +
                "from contexto,easy " +
                "where contexto.nome = easy.nomeContexto";

        Cursor cursor = database.rawQuery(query,null);

        ArrayList<Palavra> palavras = null;
        if(cursor != null && cursor.moveToFirst()){
            palavras = new ArrayList<>();

            do{

                Palavra p = new Palavra(cursor.getString(0),cursor.getString(2),Boolean.parseBoolean(cursor.getString(3)));
                if(cursor.getString(1).equals(foreignKey)) // verifica se o foreign key são iguais
                    palavras.add(p);
            }while (cursor.moveToNext());
        }
        return palavras;


    }

    public Palavra selectPalavraEasyByName(String foreignKey, String name){ // seleciona a palavra pelo nome

        ArrayList<Palavra> retorno = selectPalavraFacil(foreignKey);

        for(Palavra p : retorno){
            if(p.getNome().equals(name))
                return p;
        }
        return null;

    }

    public void deletePalavraEasy(String foreignkey, String name){ // deleta uma palavra de determinado contexto
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.delete("easy", "nomeContexto=? and nome=?", new String[]{foreignkey,name});
    }

    public void setPalavraEasy(String foreignkey, String nome, Palavra p){
        deletePalavraEasy(foreignkey,nome);
        insertPalavraFacil(foreignkey,p);
    }

}
