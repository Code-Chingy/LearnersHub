package com.techupstudio;

import java.net.URL;

public class Res {

    public static URL getResources(){ return Res.class.getResource("Resources"); }

    public static URL getLayouts() { return Res.class.getResource("Resources/Layouts"); }

    public static URL getDrawables(){ return Res.class.getResource("Resources/Drawables"); }

    public static URL getStyles(){ return Res.class.getResource("Resources/Styles"); }

    public static URL getRawData(){ return Res.class.getResource("Resources/RawData"); }

    public static URL getResource(String resourceName){
        return Res.class.getResource("Resources/" + resourceName);
    }

    public static URL getLayout(String resourceName){
        if (resourceName.contains(".")) {
            return Res.class.getResource("Resources/Layouts/"+resourceName);
        }
        return Res.class.getResource("Resources/Layouts/"+resourceName+".fxml");
    }

    public static URL getDrawable(String resourceName){
        return Res.class.getResource("Resources/Drawables/"+resourceName);
    }

    public static URL getStyle(String resourceName){
        if (resourceName.contains(".")) {
            return Res.class.getResource("Resources/Styles/"+resourceName);
        }
        return Res.class.getResource("Resources/Styles/"+resourceName+".css");
    }

    public static URL getRawData(String resourceName){
        return Res.class.getResource("Resources/RawData/"+resourceName);
    }

}
