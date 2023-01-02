package com.isetr.recylcerviewitemclick;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleViewOnItemClick {
    BottomNavigationView bottom_navigation;

    private RecyclerView recyclerView;
    private RecyclerAdapter recyclerAdapter;

//Button show_more;
    final List<NovelsModel> novelsModels = new ArrayList<>();
    final List<NovelsModel> deletedNovels = new ArrayList<>();
    final List<NovelsModel> archivedNovels = new ArrayList<>();

    private int archivedCounter = 0;
    private int deletedCounter = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        bottom_navigation=findViewById(R.id.bottom_navigation);

        bottom_navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:

                        Intent intent = new Intent(getApplicationContext(),HomePageActivity.class);
                        startActivity(intent);

                    case R.id.map:
                        Intent intent1 = new Intent(getApplicationContext(),MapActivity.class);
                        startActivity(intent1);

                    case R.id.profile:
                        Intent intent2 = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(intent2);

                    case R.id.next:
                        Intent intent3 = new Intent(getApplicationContext(),HotelsActivity.class);
                        startActivity(intent3);
                }

                return true;
            }
        });

//        show_more=findViewById(R.id.show_more);
//        show_more.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(),HotelsActivity.class);
//                startActivity(intent);
//            }
//        });

        // insert all data
        initNovelsModel();

        recyclerView = findViewById(R.id.main_rv);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new RecyclerAdapter(novelsModels, this);
        recyclerView.setAdapter(recyclerAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.LEFT |
                    ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {


            final int position = viewHolder.getAdapterPosition();
            switch (direction) {
                case ItemTouchHelper.LEFT:
                    deletedNovels.add(position, novelsModels.get(position));
                    novelsModels.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);

                    String deletedNovelsName = deletedNovels.get(deletedCounter).getNovelNames();
                    deletedCounter++;
                    Snackbar.make(recyclerView, deletedNovelsName, Snackbar.LENGTH_LONG)
                            .setAction("Undo", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    novelsModels.add(position, deletedNovels.get(position));
                                    recyclerAdapter.notifyItemInserted(position);
                                }
                            }).show();
                    break;

                case ItemTouchHelper.RIGHT:
                    archivedNovels.add(novelsModels.get(position));
                    novelsModels.remove(position);
                    recyclerAdapter.notifyItemRemoved(position);
                    String archivedNovelsName = archivedNovels.get(archivedCounter).getNovelNames();
                    archivedCounter++;
                    Snackbar.make(recyclerView, archivedNovelsName + "Archived.", Snackbar.LENGTH_LONG)
                            .setAction("View Archive", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(MainActivity.this, ArchivedActivity.class);
                                    Parcelable parcelable = Parcels.wrap(archivedNovels);
                                    intent.putExtra("archivedNovels", parcelable);
                                    startActivity(intent);

                                }
                            }).show();
                    break;
            }

        }
    };


    private void initNovelsModel() {

        novelsModels.add(new NovelsModel("الحمامات",
                "بوبوت",
                "\n" +
                        "تقع مدينة الحمامات على الخليج المعروف باسمها على الساحل الشرقي للبلاد، بين نابل  " +
                        "وسوسة. وقد كانت تسمّى قديما بوبوت (Pupput)، وقد كانت مدينة مزدهرة في العهد  " +
                        "لروماني بفضل فلاحتها ونشاطها التجاري البحري حتى ارتقت إلى مصافّ البلديّات حوالي سنة  " +
                        "176 للميلاد. وتشهد على انتشار عمرانها في عهدها القديم الآثار والنقائش المكتشفة في " +
                        "المنطقة السياحية وفي قصر الزيت والسوق الأبيض قرب المدينة الحالية، وقد أشار إلى  " +
                        "الخلاصة النقية مشيرا إلى بناء رباط أغلبي على ساحلها ضمن سلسلة من الرباطات الدفاعية."+
                "بلونها الأبيض. فقد لاحظ الحسن الوزّان المعروف باسم ليون الافريقي في بداية القرن 16 تقهقر الحمامات وفقر أهلها، وهكذا يبدو انبعاثها لم يتحقّق إلا في نهاية ق17م على حدّ ما أورده الحكيم لويس فرانك الذي كتب عن تاريخ تونس وذكر الحمّامات في كتابه المطبوع بباريس سنة 1806م معبّرا عن إعجابه بموقعها على الخليج. وفيه يقول إنّه ينبغي تصديق الأهالي في تفسيرهم لأصل تسميتها بكثرة طيور الحمام التي اتخذت أوكارها في الكهوف المجاورة، وتُلاحظ أسرابُها على المآذن والمباني المرتفعة. وقد أكّد لويس بوانصو أنّ جزءا من جامع الحمّامات يعود إلى ق 12م. وفي ذلك التاريخ وبالتحديد سنة 1148م دخل روجار الثاني ملك صقلية مدينة الحمّامات واحتل البرج لأغراض عسكرية، وفي السنة نفسها حلّت بالبلاد مجاعة كبيرة أثّرت بلا شكّ في الأهالي وحالت دون مقاومتهم للغزاة. لهذا كان لا بدّ من انتظار ظروف جديدة في العهد الحفصي عندما أمر السلطان أبو زكرياء سنة 1236م بإتمام بناء الجامع الكبير والأسوار.\n",
                R.drawable.hamamett));


        novelsModels.add(new NovelsModel("سوسة",
                "هدروماتوم",
                "\n" +
                        " بـ\"جوهرة الساحل\". والساحل التونسي منطقة تمتد على طول حوالي 170 كم بين بوفيشة والشابة وعرض 25 كم تقريبا بين سوسة وسيدي الهاني وسوسة مدينة ساحلية تشرف على شواطئ البحر الأبيض المتوسط. سواحلها رملية ملائمة للنشاط التجاري البحري ولنشاط صيد الأسماك وللنشاط السياحي. وتتكون تضاريسها من سهول وربى قليلة الارتفاع وأراضيها ملائمة للنشاط الزراعي وتربية الماشية كالغنم والبقر والماعز والدواجن. مناخها متوسطي معتدل. وكميات الأمطار النازلة فيها تتراوح بين 250 و400مم سنويا. وهو مناخ صالح لغراسة الزياتين. تحيط بالمدينة شبكة عمرانية كثيفة متباعدة بما يقدر بحوالي 5 كلمترات في المعدل وأكبر المدن المحيطة بها من حيث عدد السكان هي مساكن(مركز معتمدية) والمنستير(مركز ولاية) والمهدية(مركز ولاية) ومدن أخرى كلها مراكز معتمديات كحمام سوسة والقلعة الصغرى والقلعة الكبرى. تقع سوسة على مسافات غير كبيرة من المدن التونسية الهامة: فتقع 140 كم جنوب تونس العاصمة و50 كم شرق القيروان 20 كم غرب المنستير و 120 كم شمال صفاقس.\n" +
                        "\n" +
                        "أسسها الفينيقيون في الألف الأولى قبل الميلاد (سنة 1101 ق.م.) وتغير اسمها مرات: \n" +
                        "\n" +
                        "",
                R.drawable.sousse));

        novelsModels.add(new NovelsModel("القيروان",
                "رابعة الثلاث",
                "\n" +
                        "القَيْرَوَان مدينة تونسية، تبعد حوالي 160 كيلومتر عن تونس العاصمة والقيروان المعروفة بعاصمة الأغالبة هي أول المدن الإسلامية المشيدة في بلاد المغرب وكان لها دور استراتيجي في الفتح الإسلامي، انطلقت منها حملات الفتح نحو الجزائر والمغرب وإسبانيا وأفريقيا بالإضافة إلى أنها رقاد لعدد من صحابة رسول الله محمد صلى الله عليه وسلم ويطلق عليها الفقهاء «رابعة الثلاث،» بعد مكة و المدينة المنورة و القدس وفي هذه المدينة توجد أهم المعالم للقيروان، منها جامع القيروان الكبير و الذي أسسه عقبة بن نافع.[5]\n",+


                R.drawable.kairouan
        ));

        novelsModels.add(new NovelsModel("توزر",
                "مدينة الاحلام او باب الجنة",
                "\n" +
                        "«توزر (بالأمازيغية: ⵜⵓⵣⴻⵔ )هي مدينة وواحة صحراوية تقع في الجنوب الغربي للجمهورية التونسية ويبلغ عدد سكانها حوالي 40.000 نسمة، هي عاصمة ولاية توزر ومركز بلاد الجريد. تقع توزر في الشمال الغربي لشط الجريد وجنوبي شط الغرسة، على بعد 430 كم من العاصمة تونس.\n\n" +
                        "\n" +
                        "...",
                R.drawable.tozeur
        ));

        novelsModels.add(new NovelsModel("بنزرت",
                "هيبواكرا",
                "\n" +
                        "إن بنزرت الفينيقية أو هيبوأكرا، والتي أسست من قبل فينيقيي صيدا[6]، كانت مركزا تابعا لأوتيكا مثلما كانت تونس تابعة لقرطاج. وقد غزاها القائد الصقلي أغاطوكل سنة 309 ق.م. في حملته على قرطاج. وقد أبدت الولاء لهذه أثناء الحروب البونيقية. فلما انتصرت روما هدمت هيبو وألحقت ترابها بأوتيكا. وعند حلول يوليوس قيصر ببنزرت ضمها إلى الإمبراطورية الرومانية وأطلق عليها اسم هيبو ديارتوس. وفي عهد أغسطس قيصر جعل هذا الأخير المدينة تزدهر وتتقدم وتستعيد عمرانها ويمنحها مرتبة المدن المتمتعة بالرعاية الملكية اعترافا بموقعها الاستراتيجي الهام ومكانتها في حوض البحر الأبيض المتوسط. ولما تدهور حكم الرومان احتل (جانسريق) زعيم الوندال هيبو وذلك باستنجاد من بونيفاكيوس سنة 439 م. ولم يدم ملك الفندال طويلا بالبلاد رغم سيطرة جانسريق وابنه هانريق على كامل المنطقة المتوسطية بفضل بطولتهما الفائقة لكن خلفاءهما تشاغلوا باللهو والمجون وفرطوا في المكاسب مما تسبب في احتلال البلاد من قبل بيزنطة سنة 534 م . " +
                        "",
                R.drawable.bizerte));

        novelsModels.add(new NovelsModel("المنستير",
                "روسبينا",
                "\n" +
                        "المنستير مدينة تونسية ومركز ولاية المنستير، تعد واحدة من أهم مدن تونس. تبعد 21 كم عن سوسة و80 كم عن القيروان. يقطنها نحو نصف مليون ساكن، وهي منتجع سياحي بامتياز، ومركز تعليمي بارز، ظلت على مدى نصف قرن مدينة الوزراء والحكام في تونس، فمن بين 18 وزيرا في عهد بورقيبة منحت لمدينة الرئيس 15 حقيبة. ..\n" +

                        " ",
                R.drawable.monastir));

        novelsModels.add(new NovelsModel("دقة",
                "دقة الآثار",
                "\n" +
                        "\"دُقَّة هي مدينة أثرية (دقة الآثار) تقع في معتمدية تبرسق من ولاية باجة، بالشمال الغربي لتونس.\n" +
                        "أُدْرِجت ضمن لائحة التراث العالمي لمنظمة اليونسكو سنة 1997، باعتبارها أفضل مدينة رومانية محفوظة في شمال أفريقيا. تموقع دقّة، وسط ضيعة فلاحيّة، جعلها محميّة من الزّحف العمراني، عكس قرطاج مثلا، التي نُهبت وتمّت إعادة بناءها العديد من المرّات.\n" +
                        "تتميّز المدينة بامتدادها على مسافة 70 هكتارا، محافظتها على الآثار وبثراء تاريخها البونيقي، النّوميدي، الروماني والبيزنطي. تستمد مدينة دقة اسمها الحالي من اسمها الأمازيغي القديم وهو تُوغًّا Thugga و يعني اسمها الجّبل الصّخري.\n " +

                        "",
                R.drawable.dougadouga
        ));

        novelsModels.add(new NovelsModel("مدينة جربة",
                "جزيرة الليتوس",
                "\n" +
                        "وتعد أكبر جزر شمال أفريقيا (شريطها الساحلي طوله 125 كم)[3] وتلقب بـ«جزيرة الأحلام». تتواصل بالقارة عبر طريق يمتد على 7 كم الذي شُيد منذ العهد الروماني والذي يؤدي إلى مدينة جرجيس. كما يمكن العبور من مدينة أجيم إلى الجرف عبر البطاح (العبارة). تمتد انطلاقا من شاطئ قرية مزراية شبه جزيرة رأس الرمل وهي من الأقطاب السياحية المميزة للجزيرة. أعجب بها الأديب التّونسيّ إبراهيم درغوثي أيّما إعجاب فحرّر نصّا غاية في الإبداع تحت عنوان «جربة الّتي في خاطري». ويسمى سكان جربة الجرابة.أدرجت جربة ضمن قائمة التراث العالمي " +
                        ".",
                R.drawable.djerba
        ));

        novelsModels.add(new NovelsModel("عين دراهم",
                "عين المال",
                "\n" +
                        "كانت منطقة عين دراهم انطلاقا لقاعدة عسكرية فرنسية [7] ، وهي المنطقة الثالثة بعد فرنانة ومزارة السردوك التي تفضلها القوات العسكرية [8] ؛ كما مثلت قرية تشمل المرافق الأساسية للمستوطنين (التعليم، التجارة، استغلال الغابات، الخ.). كما أنها، سنة 1892، من أولى البلديات التونسية القائمة آنذاك.\n" +
                        "\n" +
                        "سنة 1930، أصبحت محطة سياحية متعددة الوظائف [9] معدة لاحتضان المستوطنين الفرنسيين (المنح العائلية، الإقامات، الإدارات، الخ.). ولا بد من الإشارة إلى أن طبيعة المدينة الغابية وموروثها الإستعماري (الهندسة المعمارية، القرميد الأحمر، والمنتوجات التقليدية) تمثل ميزات تدعم السياحة الداخلية بها : حرارة ملطفة في فصل الصيف، غابة غنية بعدة حيوانات كالخنزير البري، مسالك صحية ورياضية تمارس بها رياضة المشي، ركوب الدراجة أو ركوب الخيل، السياحة البيئية والرياضية والإستشفائية.\n" +
                        "\n" +
                        "ابتداءا من سنة 1982 [10] ، وبسبب قرارات سياسية، أصبحت المدينة عنصرا من مشروع سياحة الكم التي تربط الغابة بالبحر (المحطة المندمجة بطبرقة " +
                        "",
                R.drawable.gend
        ));

        novelsModels.add(new NovelsModel("زغوان",
                "اكوا", "\n" +
                "ولاية زغوان التي تأسست سنة 1976، هي إحدى ولايات تونس الأربعة والعشرين. تبلغ مساحتها 2.768 كم² (أي 1,7% من مساحة البلاد) وتعداد سكانها سنة [6]2014 176.945 نسمة (وهي تحتل بذلك المرتبة 21 بين ولايات تونس من حيث عدد السكان). مركزها مدينة زغوان. " +
                " ",
                R.drawable.zagh
        ));


    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this, ShowMoreActivity.class);

        intent.putExtra("book_images", novelsModels.get(position).getNovelImage());

        intent.putExtra("book_intros", novelsModels.get(position).getNovelIntros());

        startActivity(intent);
    }

    @Override
    public void onLongItemClick(int position) {

        novelsModels.remove(position);
        recyclerAdapter.notifyItemRemoved(position);
    }



}
