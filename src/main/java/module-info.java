module ru.hukola.foldertree {
    requires javafx.controls;


    opens ru.hukola.foldertree.model to javafx.base;
    opens ru.hukola.foldertree to javafx.base;
    opens ru.hukola.foldertree.view to javafx.base;
    opens ru.hukola.foldertree.controller to javafx.base;
    
    exports ru.hukola.foldertree;
}