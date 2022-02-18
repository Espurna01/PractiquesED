package EstructuraDades;

import Excepcions.elementNoExisteix;
import Excepcions.operacioImposible;

public class DLL<T> implements TADLlista<T> {

    private DLL<T> fwd;
    private DLL<T> bkw;
    private T data;

    public DLL(T data){
        crear();
        this.data = data;
    }

    public DLL(){
        this(null);
    }

    public DLL(T data, DLL<T> bkw){
        crear();
        this.data = data;
        this.bkw = bkw;
    }

    @Override
    public void crear() {
        fwd = null;
        bkw = null;
    }

    @Override
    public void inserir(T data) {
        if(data != null){
            if(this.data == null){
                this.data = data;
            }else{
                if(fwd != null)
                    fwd.inserir(data);
                else
                    fwd = new DLL<>(data, this);
            }
        }
    }

    @Override
    public void inserir(int posició, T data) throws operacioImposible {

    }

    @Override
    public T obtenir(int posició) throws operacioImposible {
        return null;
    }

    @Override
    public int longitud() {
        return 0;
    }

    @Override
    public void Esborrar(int posició) throws operacioImposible {

    }

    @Override
    public int Buscar(T data) throws elementNoExisteix {
        return 0;
    }
}
