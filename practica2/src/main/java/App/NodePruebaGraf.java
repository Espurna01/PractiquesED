package main.java.App;

import main.java.DataStructure.ComplementaryStructures.ArestaPruebaGraf;

public class NodePruebaGraf  {
        private String info;
        private ArestaPruebaGraf prim_fil;
        private ArestaPruebaGraf prim_col;

        public NodePruebaGraf(String info){
            this.info = info;
            prim_col = null;
            prim_fil = null;
        }

        public String getInfo() {
            return info;
        }

        public ArestaPruebaGraf getPrim_fil() {
            return prim_fil;
        }

        public void setPrim_fil(ArestaPruebaGraf prim_fil) {
            this.prim_fil = prim_fil;
        }

        public ArestaPruebaGraf getPrim_col() {
            return prim_col;
        }

        public void setPrim_col(ArestaPruebaGraf prim_col) {
            this.prim_col = prim_col;
        }

        public String toString(){
            return info;
        }

        @Override
        public boolean equals(Object obj) {
            return info.equals(((main.java.DataStructure.ComplementaryStructures.NodeGraf<?, ?>)obj).getInfo());
        }



}
