package main.java.DataStructure.ComplementaryStructures;

import main.java.App.NodePruebaGraf;

public class ArestaPruebaGraf {
    protected ArestaPruebaGraf seg_fil;
    protected ArestaPruebaGraf seg_col;
    protected NodePruebaGraf node_fil;
    protected NodePruebaGraf node_col;

    protected String info;

    public ArestaPruebaGraf(String info) {
        this.node_fil = null;
        this.node_col = null;
        seg_col = null;
        seg_fil = null;
        this.info = info;
    }

    public ArestaPruebaGraf getSeg_fil() {
        return seg_fil;
    }

    public void setSeg_fil(ArestaPruebaGraf seg_fil) {
        this.seg_fil = seg_fil;
    }

    public ArestaPruebaGraf getSeg_col() {
        return seg_col;
    }

    public void setSeg_col(ArestaPruebaGraf seg_col) {
        this.seg_col = seg_col;
    }

    public NodePruebaGraf getNode_fil() {
        return node_fil;
    }

    public void setNode_fil(NodePruebaGraf node_fil) {
        this.node_fil = node_fil;
    }

    public NodePruebaGraf getNode_col() {
        return node_col;
    }

    public void setNode_col(NodePruebaGraf node_col) {
        this.node_col = node_col;
    }

    public String getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return node_col + "(" + info +")";
    }
}
