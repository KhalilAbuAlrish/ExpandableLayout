package com.example.expandablelayout.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expandablelayout.Module.ChildModule;
import com.example.expandablelayout.Module.ParentModule;
import com.example.expandablelayout.R;

import java.util.ArrayList;
import java.util.List;

import iammert.com.expandablelib.ExpandableLayout;
import iammert.com.expandablelib.Section;

public class MainActivity extends AppCompatActivity {


    ExpandableLayout expandableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableLayout=findViewById(R.id.expandable_layout);

        expandableLayout.setRenderer(new ExpandableLayout.Renderer<ParentModule, ChildModule>() {
            @Override
            public void renderParent(View view, ParentModule model, boolean isExpanded, int parentPosition) {

                TextView nameParent=view.findViewById(R.id.parent_name);
                ImageView arrowImage=view.findViewById(R.id.arrow);

                nameParent.setText(model.getName());

                arrowImage.setBackgroundResource(isExpanded ? R.drawable.arrow_up : R.drawable.arrow_down);

            }

            @Override
            public void renderChild(View view, ChildModule model, int parentPosition, int childPosition) {

                TextView nameChild=view.findViewById(R.id.child_name);
                nameChild.setText(model.getNameChild());

                nameChild.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Toast.makeText(MainActivity.this, nameChild.getText()+"", Toast.LENGTH_SHORT).show();
                        
                    }
                });



            }
        });

        expandableLayout.addSection(getSection("parent 1"));
        expandableLayout.addSection(getSection("parent 2"));
        expandableLayout.addSection(getSection("parent 3"));

    }


    private Section<ParentModule,ChildModule>getSection(String parentName){

        Section<ParentModule,ChildModule>section=new Section<>();

        ParentModule parentModule=new ParentModule();
        parentModule.setName(parentName);

        List<ChildModule> list=new ArrayList<>();

        for(int i=1;i<=5;i++){
            ChildModule childModule=new ChildModule();
            childModule.setNameChild(parentName+" "+i);
            list.add(childModule);
        }

        section.parent=parentModule;
        section.children.addAll(list);



        return section;

    }



}