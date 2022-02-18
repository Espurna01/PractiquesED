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
        DLL<T> poi = this;
        for(int i = 0; i < posició;i++){
            poi = poi.fwd;
        }

    }

    @Override
    public T obtenir(int posició) throws operacioImposible {
        DLL<T> poi = this;
        for(int i = 0; i < posició;i++){
            poi = poi.fwd;
        }
        return poi.data;
    }

    @Override
    public int longitud() {
        if(data == null){
            return 0;
        } else{
            DLL<T> poi = this;
            int i = 0;
            for(;fwd != null;i++){
                i++;
                poi = poi.fwd;
            }
            return i+1;
        }
    }

    @Override
    public void Esborrar(int posició) throws operacioImposible {

    }

    @Override
    public int Buscar(T data) throws elementNoExisteix {
        return 0;
    }
}
