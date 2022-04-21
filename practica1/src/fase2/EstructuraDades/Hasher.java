package fase2.EstructuraDades;

public class Hasher {

    //Overload
    public static int getHash(Object toHash){
        if (toHash instanceof Integer)
            return getHash((Integer) toHash);
        return getHash(toHash.toString());
    }

    //Overload
    public static int getHash(String toHash){
        int result = 0;
        //toHash = toHash.replaceAll(" ", "").trim();
        char[] chars = new char[toHash.length()];
        toHash.getChars(0, toHash.length(), chars, 0);

        for(int i = 0;i < toHash.length(); i++){
            result = result^(int) chars[i];
        }
        return result;
    }

    //Overload
    public static int getHash(Integer toHash){
        return toHash;
    }


}
