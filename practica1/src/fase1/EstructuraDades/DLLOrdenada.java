package fase1.EstructuraDades;

import fase1.Excepcions.operacioImpossible;

public class DLLOrdenada<T extends Comparable<T>> extends DLL<T> {

    private boolean menorMajor;                 //false: <, true: >

    public DLLOrdenada(boolean menorMajor){
        super();
        this.menorMajor = menorMajor;
    }


    public void inserir(T data){
        if(data != null){
            if (this.data != null) {
                DLL<T> poi = this;

                boolean dreta;
                if(menorMajor){
                    while(poi.getFwd() != null && data.compareTo(poi.getData()) >= 0){
                        poi = poi.getFwd();
                    }
                    dreta = data.compareTo(poi.getData()) >= 0;
                }else{
                    while(poi.getFwd() != null && data.compareTo(poi.getData()) <= 0){
                        poi = poi.getFwd();
                    }
                    dreta = data.compareTo(poi.getData()) <= 0;
                }
                if(dreta){
                    DLL<T> nnode = new DLL<>(data, poi);
                    nnode.setFwd(poi.getFwd());
                    poi.setFwd(nnode);
                    if(nnode.getFwd() != null)
                        nnode.getFwd().setBkw(nnode);
                }else{
                    if(poi.getBkw() == null){
                        DLL<T> nnode = new DLL<>(this.data, this);
                        if(getFwd() != null)
                            getFwd().setBkw(nnode);
                        nnode.setFwd(getFwd());
                        this.data = data;
                        setFwd(nnode);
                    }else{
                        DLL<T> nnode = new DLL<>(data, poi.getBkw());
                        nnode.setFwd(poi);
                        poi.getBkw().setFwd(nnode);
                        poi.setBkw(nnode);
                    }
                }

            } else
                this.data = data;

        }
    }

    @Override
    public void inserir(int posicio, T data) throws operacioImpossible {
        throw new operacioImpossible(posicio);
    }
}
