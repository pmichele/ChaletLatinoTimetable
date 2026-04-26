import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Michele Pettinato
 */
public class Main {

    static final int TIME_SLOTS = 7,
    CLASSROOM_ONE_ID = 0,
    CLASSROOM_TWO_ID = 1,
            CLASSROOM_THREE_ID = 2,
    NUM_CLASSROOMS = 3;
    static final float INFINITY = Integer.MAX_VALUE, SCORE_LIMIT = 1_000_000_000.0f;
    public static final int LAST_SATURDAY_TIMESLOT = 4;
    public static final int CALENA = 6;
    public static final int AFTER_SATURDAY_LUNCH = 2;
    public static final float INTEREST_WEIGHT = 0.2f;
    public static final int REGGAE = 1;
    public static final int URBAN_SALSA = 0;
    public static final int NOT_INTERESTED = 0;
    public static final int I_DONT_WANNA_MISS_IT = 2;
    public static final int INTERESTED = 1;
    public static final int ILA = 26;
    public static final int LISON = 39;
    public static final int TEYA = 74;
    public static final int JULIE = 28;
    public static final int PA = 58;
    public static final int PATRICK = 60;
    public static final int LUCA = 64;
    public static final int FIRST_CLASS = 0;
    public static final int STYLING_ID = 6;
    public static final int BORIS_CLASS_2 = 4;
    public static final int BORIS_CLASS_1 = 3;
    public static final int SATURDAY_MORNING = 1;
    public static final int TEYA_CLASS = 0;
    public static final int AERIAL_LIFT_CLASS = 5;

    /* Dataset */

    static List<Integer> interSalsa = Arrays.asList(0, 3, 4, 6);
    static String[] salsaClasses = new String[]{
            "Urban Salsa (Intermediate) – PA & Ilaria",
            "Afro-Cuban Motion (Advanced) – Ilaria",
            "Salsa Cubana Man Styling (Advanced) – PA",
            "Son Cubano (Intermediate)– Laurent & Mariana",
            "Musicality - Deep Dive (Intermediate) – Laurent & Mariana",
            "Salsa Figures (Advanced) – Anaïse & Kai",
            "Salsa Caleña (Beginner-Intermediate) – Luca",
    };
    static List<Integer> interBachata = Arrays.asList(0, 3, 4, 5);
    static String[] bachataClasses = new String[]{
            "Funky Bachata Moves (Intermediate) — Teya & Alic",
            "Bachata fusion (Advanced) – Lison & Patrick",
            "Bachazouk basics (Inter-Advanced) – Patrick & Julie",
            "Pasitos Flow — Boris & Morgana",
            "Styling in partnerwork — Boris & Morgana",
            "Bachata Sensual (Intermediate) – Alex & Julia",
    };
    static String[] discoveryClasses = new String[]{
            "Lyrical hip hop — Ilaria",
            "Reggaeton — Ilaria",
            "Viennese Waltz — Lison & Luca",
            "Ballroom Rumba — Lison & Luca",
            "Easy Tricks & Dips – Anaïse & Kai",
            "Aerial Lifts – Anaïse & Kai",
            "Leader & Follower - Find your style — Luca & Lison",
    };
    static String[] participants = {
            "Alberto Zirondelli", //0
            "Alessandra Aloulou Raposo", //1
            "Alexandre Elsig", //2
            "Alic Kaufmann", //3
            "Alice Moraz", //4
            "Alicia Pérez Domouso", //5
            "Alina Brüllhardt", //6
            "Anaïse Vallée", //7
            "Annamira O'Toole", //8
            "Asia Lahici", //9
            "Brian Lupton", //10
            "Brune Bettler", //11
            "Céline", //12
            "Claudia La Valle", //13
            "David Arroyave", //14
            "David Barioni", //15
            "Dennis Makarov", //16
            "Diego Clavijo", //17
            "Dylan Samuelian", //18
            "Fabrice Demière", //19
            "Felix Hans Michel Grimberg", //20
            "Georg Teufelberger", //21
            "Géraldine Keller", //22
            "Giuseppe Marino", //23
            "Heidi", //24
            "Igor Krawczuk", //25
            "Ilaria Ricchi", //26
            "Janine Vögele", //27
            "Julie Hernandez", //28
            "Jusef Akbari", //29
            "Kai Ott", //30
            "Kaisu Hiltunen", //31
            "Kalila Hörler", //32
            "Kevin Steiner", //33
            "Kruszynska Julia", //34
            "Larissa Schuh", //35
            "Laszlo Demko", //36
            "Laurent Bugnard", //37
            "Laurine Gasser", //38
            "Lison Ravassard", //39
            "Lissy Reim", //40
            "Lucijana Stanic", //41
            "Maja Stamenkovic", //42
            "Manuel Bernal Lecina", //43
            "Mariana Leon", //44
            "Mariona Lopez Gil", //45
            "Matteo Mancuso", //46
            "Matthieu", //47
            "Mélanie Kugler", //48
            "Melany Falcon", //49
            "Michele Pettinato", //50
            "Miguel BASANTE-BEDOYA", //51
            "Mihael Vujicic", //52
            "Milena Schuhmacher", //53
            "Natali Gomez", //54
            "Nathalie Aney Guzmán Santa Cruz", //55
            "Noa Varela Cinquegrani", //56
            "Oleg Pulatov", //57
            "P.A.", //58
            "Pablo Garcia", //59
            "Patrick Sgrò", //60
            "Reto Zihlmann", //61
            "Richard Santiago", //62
            "Romie Lee", //63
            "Ruef Luca", //64
            "Ruth Tuschewski", //65
            "Saira Vögeli", //66
            "Sami Bouziri", //67
            "Samuel Widmer", //68
            "Sara Zatezalo", //69
            "Seungchan Hwang", //70
            "Sofia De Angelus", //71
            "Sophia Boyer", //72
            "Talissa Rodriguez", //73
            "Teya Petrova", //74
            "Timo Spring", //75
            "Tristan Reinhard", //76
            "Veronica Edwards", //77
            "vladyslav korobeynyk", //78
    };


    static int[][] participantsSalsaChoices = {
            {2, 2, 2, 1, 2, 2, 0}, //0
            {2, 0, 0, 1, 1, 2, 2}, //1
            {0, 0, 0, 0, 0, 0, 0}, //2
            {0, 2, 0, 0, 2, 0, 0}, //3
            {2, 2, 0, 2, 1, 1, 0}, //4
            {2, 1, 0, 1, 2, 2, 1}, //5
            {2, 0, 0, 0, 1, 1, 1}, //6
            {1, 2, 0, 1, 1, 2, 1}, //7
            {2, 1, 0, 1, 1, 0, 2}, //8
            {1, 2, 0, 2, 2, 1, 2}, //9
            {1, 1, 2, 1, 2, 2, 0}, //10
            {1, 1, 0, 0, 2, 2, 0}, //11
            {2, 1, 0, 0, 2, 2, 0}, //12
            {1, 2, 0, 1, 2, 2, 1}, //13
            {2, 0, 0, 1, 1, 2, 2}, //14
            {0, 0, 0, 2, 2, 1, 2}, //15
            {2, 2, 2, 0, 1, 1, 1}, //16
            {1, 1, 1, 2, 2, 2, 1}, //17
            {2, 0, 0, 0, 1, 1, 2}, //18
            {1, 0, 1, 0, 1, 1, 0}, //19
            {1, 1, 1, 0, 1, 2, 1}, //20
            {1, 1, 2, 1, 2, 0, 1}, //21
            {0, 1, 0, 0, 0, 1, 0}, //22
            {1, 1, 2, 1, 1, 2, 0}, //23
            {2, 1, 1, 1, 2, 1, 1}, //24
            {2, 1, 1, 1, 2, 1, 0}, //25
            {2, 2, 0, 1, 1, 0, 0}, //26
            {2, 1, 0, 0, 2, 1, 1}, //27
            {1, 0, 0, 0, 1, 1, 1}, //28
            {1, 1, 2, 2, 1, 2, 1}, //29
            {1, 1, 1, 0, 0, 2, 0}, //30
            {2, 2, 1, 1, 2, 2, 1}, //31
            {1, 1, 0, 1, 2, 1, 1}, //32
            {1, 2, 2, 0, 1, 0, 0}, //33
            {2, 1, 1, 1, 1, 0, 2}, //34
            {1, 2, 0, 0, 1, 1, 0}, //35
            {1, 0, 0, 0, 1, 2, 1}, //36
            {1, 1, 1, 2, 2, 1, 1}, //37
            {0, 0, 0, 2, 2, 1, 1}, //38
            {0, 0, 0, 0, 0, 0, 2}, //39
            {1, 1, 0, 0, 1, 2, 0}, //40
            {1, 1, 1, 1, 2, 0, 0}, //41
            {1, 2, 0, 2, 2, 1, 0}, //42
            {1, 0, 1, 2, 1, 1, 0}, //43
            {2, 2, 0, 2, 2, 1, 0}, //44
            {2, 0, 0, 2, 1, 2, 2}, //45
            {0, 0, 0, 1, 2, 2, 1}, //46
            {1, 2, 1, 1, 2, 1, 1}, //47
            {2, 1, 0, 1, 0, 1, 2}, //48
            {0, 0, 0, 1, 1, 0, 2}, //49
            {1, 2, 1, 0, 0, 0, 1}, //50
            {1, 2, 1, 2, 1, 2, 2}, //51
            {1, 1, 2, 1, 2, 2, 0}, //52
            {2, 1, 2, 2, 1, 2, 1}, //53
            {2, 0, 0, 2, 2, 0, 2}, //54
            {2, 0, 0, 2, 2, 2, 2}, //55
            {1, 2, 0, 2, 2, 0, 1}, //56
            {0, 0, 2, 2, 2, 2, 0}, //57
            {2, 2, 2, 0, 1, 0, 0}, //58
            {1, 1, 2, 1, 2, 1, 1}, //59
            {2, 2, 1, 0, 1, 0, 1}, //60
            {1, 0, 1, 0, 0, 2, 0}, //61
            {1, 1, 1, 2, 2, 2, 1}, //62
            {2, 1, 0, 1, 1, 2, 1}, //63
            {0, 0, 1, 0, 1, 2, 2}, //64
            {2, 0, 0, 1, 2, 0, 1}, //65
            {1, 0, 0, 0, 2, 1, 0}, //66
            {1, 0, 2, 2, 2, 2, 1}, //67
            {1, 0, 1, 2, 2, 1, 2}, //68
            {1, 0, 0, 2, 2, 2, 1}, //69
            {2, 1, 1, 1, 2, 1, 1}, //70
            {0, 2, 0, 0, 0, 0, 2}, //71
            {2, 1, 0, 1, 0, 2, 0}, //72
            {2, 2, 0, 2, 2, 0, 0}, //73
            {1, 1, 0, 0, 1, 0, 1}, //74
            {1, 0, 1, 1, 1, 1, 1}, //75
            {1, 0, 1, 0, 0, 2, 0}, //76
            {0, 2, 0, 1, 2, 1, 2}, //77
            {0, 2, 2, 0, 2, 2, 2}, //78

    };
    static int[][] participantsBachataChoices = {
            {1, 0, 0, 1, 0, 1}, //0
            {0, 0, 0, 1, 0, 0}, //1
            {2, 2, 2, 2, 2, 0}, //2
            {2, 2, 2, 1, 2, 2}, //3
            {1, 0, 0, 2, 2, 2}, //4
            {1, 2, 1, 2, 2, 1}, //5
            {0, 0, 0, 0, 0, 0}, //6
            {0, 1, 0, 1, 1, 0}, //7
            {1, 1, 0, 0, 0, 0}, //8
            {0, 0, 0, 2, 0, 0}, //9
            {2, 1, 2, 1, 1, 2}, //10
            {1, 1, 0, 2, 1, 0}, //11
            {2, 2, 2, 2, 2, 1}, //12
            {2, 2, 2, 1, 2, 1}, //13
            {0, 0, 0, 1, 0, 0}, //14
            {2, 2, 1, 0, 2, 2}, //15
            {1, 0, 1, 2, 2, 0}, //16
            {1, 1, 0, 1, 1, 1}, //17
            {2, 0, 0, 0, 0, 2}, //18
            {1, 2, 2, 1, 1, 1}, //19
            {0, 0, 0, 0, 0, 0}, //20
            {2, 1, 0, 2, 2, 1}, //21
            {2, 1, 2, 0, 1, 1}, //22
            {1, 2, 1, 1, 1, 1}, //23
            {1, 1, 2, 1, 1, 1}, //24
            {1, 1, 2, 1, 1, 2}, //25
            {0, 1, 0, 1, 1, 0}, //26
            {1, 2, 1, 0, 2, 2}, //27
            {2, 1, 2, 2, 2, 1}, //28
            {1, 2, 2, 1, 1, 2}, //29
            {0, 2, 1, 0, 0, 0}, //30
            {2, 1, 1, 1, 2, 2}, //31
            {1, 2, 2, 2, 2, 1}, //32
            {1, 0, 0, 1, 0, 0}, //33
            {1, 2, 2, 1, 2, 2}, //34
            {1, 1, 1, 2, 1, 1}, //35
            {1, 0, 1, 1, 1, 1}, //36
            {1, 0, 1, 0, 0, 0}, //37
            {2, 2, 0, 1, 1, 1}, //38
            {2, 2, 2, 1, 1, 1}, //39
            {1, 2, 2, 1, 2, 0}, //40
            {2, 1, 0, 2, 2, 0}, //41
            {0, 0, 0, 1, 0, 0}, //42
            {1, 0, 0, 1, 0, 2}, //43
            {1, 1, 1, 1, 1, 1}, //44
            {2, 0, 0, 2, 2, 2}, //45
            {1, 2, 2, 1, 1, 0}, //46
            {0, 0, 0, 0, 0, 0}, //47
            {1, 0, 0, 1, 1, 1}, //48
            {1, 2, 2, 1, 2, 2}, //49
            {1, 0, 0, 0, 0, 0}, //50
            {0, 0, 0, 0, 0, 0}, //51
            {2, 2, 0, 1, 1, 0}, //52
            {2, 1, 1, 2, 2, 2}, //53
            {0, 0, 0, 0, 0, 0}, //54
            {2, 2, 2, 2, 2, 2}, //55
            {2, 0, 1, 0, 1, 0}, //56
            {1, 1, 1, 0, 0, 1}, //57
            {1, 2, 2, 0, 0, 0}, //58
            {1, 0, 0, 2, 2, 0}, //59
            {1, 2, 2, 1, 0, 0}, //60
            {2, 1, 0, 0, 0, 1}, //61
            {0, 0, 0, 0, 0, 0}, //62
            {2, 1, 1, 1, 2, 1}, //63
            {1, 2, 1, 1, 1, 1}, //64
            {1, 0, 0, 1, 1, 1}, //65
            {1, 0, 1, 0, 2, 1}, //66
            {1, 1, 0, 1, 1, 2}, //67
            {1, 1, 2, 1, 1, 1}, //68
            {1, 0, 1, 0, 1, 0}, //69
            {1, 0, 2, 2, 2, 0}, //70
            {1, 1, 2, 1, 0, 0}, //71
            {2, 1, 1, 1, 2, 0}, //72
            {0, 0, 0, 0, 0, 0}, //73
            {2, 2, 2, 2, 2, 2}, //74
            {2, 2, 2, 1, 1, 1}, //75
            {2, 2, 1, 2, 2, 2}, //76
            {0, 0, 0, 1, 0, 0}, //77
            {0, 0, 0, 0, 0, 0}, //78
    };

    static int[][] participantsDiscoveryChoices = {
            {1, 0, 0, 0, 1, 2, 2}, //0
            {0, 0, 0, 0, 2, 2, 0}, //1
            {1, 1, 0, 0, 0, 0, 2}, //2
            {2, 2, 2, 0, 0, 0, 0}, //3
            {2, 1, 1, 0, 1, 1, 2}, //4
            {1, 2, 1, 2, 1, 1, 2}, //5
            {0, 0, 1, 1, 2, 0, 1}, //6
            {1, 1, 0, 0, 2, 2, 1}, //7
            {0, 1, 0, 0, 2, 0, 1}, //8
            {0, 1, 0, 0, 0, 0, 0}, //9
            {1, 1, 0, 1, 1, 2, 1}, //10
            {1, 1, 0, 2, 0, 0, 2}, //11
            {0, 0, 0, 0, 1, 2, 1}, //12
            {2, 2, 0, 1, 1, 1, 1}, //13
            {0, 0, 0, 0, 2, 2, 0}, //14
            {0, 0, 2, 2, 1, 1, 2}, //15
            {2, 2, 0, 0, 1, 2, 2}, //16
            {0, 1, 0, 0, 2, 1, 1}, //17
            {0, 0, 2, 1, 0, 0, 1}, //18
            {2, 2, 0, 0, 1, 1, 1}, //19
            {0, 0, 1, 1, 1, 1, 2}, //20
            {2, 2, 0, 0, 0, 0, 2}, //21
            {1, 1, 2, 2, 1, 0, 1}, //22
            {0, 2, 0, 0, 1, 2, 2}, //23
            {1, 2, 0, 0, 1, 2, 1}, //24
            {1, 2, 1, 0, 2, 2, 2}, //25
            {2, 2, 1, 1, 1, 1, 1}, //26
            {0, 0, 0, 0, 2, 2, 1}, //27
            {1, 1, 1, 1, 0, 0, 1}, //28
            {1, 1, 1, 1, 2, 2, 1}, //29
            {0, 1, 0, 0, 2, 2, 0}, //30
            {1, 1, 1, 1, 2, 1, 1}, //31
            {0, 0, 0, 0, 2, 0, 2}, //32
            {0, 1, 0, 0, 0, 0, 1}, //33
            {2, 2, 0, 0, 0, 1, 1}, //34
            {0, 1, 0, 1, 2, 2, 1}, //35
            {0, 1, 1, 1, 1, 0, 1}, //36
            {0, 1, 1, 1, 1, 1, 1}, //37
            {0, 1, 1, 1, 1, 2, 2}, //38
            {0, 0, 2, 2, 0, 0, 2}, //39
            {0, 1, 0, 0, 2, 2, 2}, //40
            {2, 2, 0, 0, 0, 0, 2}, //41
            {1, 1, 2, 2, 0, 0, 2}, //42
            {0, 1, 2, 2, 2, 1, 1}, //43
            {1, 2, 0, 0, 0, 0, 1}, //44
            {0, 0, 0, 0, 1, 0, 1}, //45
            {0, 0, 0, 0, 2, 2, 0}, //46
            {1, 1, 0, 0, 0, 1, 0}, //47
            {1, 2, 0, 2, 2, 2, 2}, //48
            {0, 1, 1, 2, 0, 0, 1}, //49
            {2, 2, 2, 1, 2, 1, 0}, //50
            {0, 0, 0, 1, 1, 0, 2}, //51
            {0, 1, 1, 2, 1, 0, 2}, //52
            {1, 1, 2, 2, 2, 1, 2}, //53
            {1, 1, 1, 2, 2, 2, 2}, //54
            {0, 2, 2, 2, 0, 0, 0}, //55
            {0, 0, 2, 2, 0, 1, 2}, //56
            {0, 0, 0, 0, 2, 2, 2}, //57
            {2, 2, 0, 0, 0, 2, 0}, //58
            {1, 1, 0, 0, 2, 0, 2}, //59
            {1, 2, 0, 1, 0, 1, 1}, //60
            {0, 0, 0, 0, 1, 1, 0}, //61
            {1, 1, 0, 0, 0, 0, 1}, //62
            {0, 1, 1, 1, 2, 2, 1}, //63
            {0, 1, 2, 2, 0, 0, 2}, //64
            {1, 2, 1, 1, 1, 0, 2}, //65
            {0, 1, 0, 0, 1, 0, 1}, //66
            {0, 1, 0, 0, 2, 2, 2}, //67
            {1, 2, 0, 1, 1, 1, 1}, //68
            {1, 2, 1, 0, 1, 1, 2}, //69
            {1, 1, 0, 0, 2, 2, 1}, //70
            {2, 1, 0, 0, 0, 2, 0}, //71
            {1, 2, 0, 0, 1, 2, 0}, //72
            {0, 2, 0, 2, 0, 0, 2}, //73
            {0, 2, 1, 1, 1, 0, 2}, //74
            {0, 1, 0, 0, 0, 0, 1}, //75
            {0, 1, 0, 0, 1, 1, 1}, //76
            {0, 1, 0, 0, 1, 1, 2}, //77
            {0, 1, 0, 0, 0, 1, 0}, //78

    };


    static int[] salsaVotes, salsaInterestVotes;
    static int[] bachataVotes, bachataInterestVotes;
    static int[] discoveryVotes, discoveryInterestVotes;

    static int[] howManyChoices, howManyInterests;


    public static void main(String[] args) {

        salsaVotes = new int[salsaClasses.length];
        bachataVotes = new int[bachataClasses.length];
        discoveryVotes = new int[discoveryClasses.length];

        salsaInterestVotes = new int[salsaClasses.length];
        bachataInterestVotes = new int[bachataClasses.length];
        discoveryInterestVotes = new int[discoveryClasses.length];

        /* People on duties (kitchen). They should be 5 on 1 4 and 6 */
        Set<Integer>[] duties = new Set[TIME_SLOTS];
        duties[0] = Set.of();
        duties[1] = Set.of(TEYA, 3, 29, 10, 40);
        duties[2] = Set.of();
        duties[3] = Set.of();
        duties[4] = Set.of(44, 47, 54, 73, 45);
        duties[5] = Set.of();
        duties[6] = Set.of(43, 1, 14, 5, 11);

        /* Teaching duties */
        List<Integer>[] salsaProfs = new List[salsaClasses.length],
                bachataProfs = new List[bachataClasses.length],
                discoveryProfs = new List[discoveryClasses.length];
        // see participantNames for the name of the profs
        salsaProfs[0] = List.of(ILA, PA);
        salsaProfs[1] = List.of(ILA);
        salsaProfs[2] = List.of(PA);
        salsaProfs[3] = List.of(37, 44);
        salsaProfs[4] = List.of(37, 44);
        salsaProfs[5] = List.of(7, 30);
        salsaProfs[6] = List.of(LUCA);
        bachataProfs[0] = List.of(TEYA, 3);
        bachataProfs[1] = List.of(PATRICK, LISON);
        bachataProfs[2] = List.of(PATRICK, JULIE);
        bachataProfs[3] = List.of();
        bachataProfs[4] = List.of();
        bachataProfs[5] = List.of(2, 34);
        discoveryProfs[0] = List.of(ILA);
        discoveryProfs[1] = List.of(ILA);
        discoveryProfs[2] = List.of(LISON, LUCA);
        discoveryProfs[3] = List.of(LISON, LUCA);
        discoveryProfs[4] = List.of(30, 7);
        discoveryProfs[5] = List.of(30, 7);
        discoveryProfs[6] = List.of(LUCA, LISON);

        /* Data cleaning : remove profs that voted for their own class BEFORE using the data */
        for (int s = 0; s < salsaProfs.length; ++s) {
            for (Integer prof : salsaProfs[s]) {
                participantsSalsaChoices[prof][s] = NOT_INTERESTED;
            }
        }
        for (int b = 0; b < bachataProfs.length; ++b) {
            for (Integer prof : bachataProfs[b]) {
                participantsBachataChoices[prof][b] = NOT_INTERESTED;
            }
        }
        for (int d = 0; d < discoveryProfs.length; ++d) {
            for (Integer prof : discoveryProfs[d]) {
                participantsDiscoveryChoices[prof][d] = NOT_INTERESTED;
            }
        }

        /* Hard constraint: Profs cannot teach when they are unavailable, like due to duties. (automated unless special case Patrick is not the masterchef anymore, because he wants to be always on duty)
         * Note: these are not used for loss computation, so patrick does not get a boost in the loss, which is correct because he needs to be there just in case but his choices will not count */
        Set<Integer>[] salsaProfsUnavailable = new Set[salsaClasses.length];
        Set<Integer>[] bachataProfsUnavailable = new Set[bachataClasses.length];
        Set<Integer>[] discoveryProfsUnavailable = new Set[discoveryClasses.length];
        for (int c = 0; c < salsaClasses.length; ++c) {
            salsaProfsUnavailable[c] = new HashSet<>();
            for (int d = 0; d < duties.length; ++d) {
                if (salsaProfs[c].stream().anyMatch(duties[d]::contains)) {
                    salsaProfsUnavailable[c].add(d);
                }
            }
//            System.out.println(salsaProfsUnavailable[c]);
        }
        for (int c = 0; c < bachataClasses.length; ++c) {
            bachataProfsUnavailable[c] = new HashSet<>();
            for (int d = 0; d < duties.length; ++d) {
                // ! SPECIAL CASE FOR PATRICK !
                boolean isKitchenTime = d == 1 || d == 4 || d == 6;
                boolean isPatrickSpecialCase = bachataProfs[c].contains(PATRICK) && isKitchenTime;
                if (isPatrickSpecialCase || bachataProfs[c].stream().anyMatch(duties[d]::contains)) {
                    bachataProfsUnavailable[c].add(d);
                }
            }
//            System.out.println(bachataProfsUnavailable[c]);
        }
        for (int c = 0; c < discoveryClasses.length; ++c) {
            discoveryProfsUnavailable[c] = new HashSet<>();
            for (int d = 0; d < duties.length; ++d) {
                if (discoveryProfs[c].stream().anyMatch(duties[d]::contains)) {
                    discoveryProfsUnavailable[c].add(d);
                }
            }
//            System.out.println(discoveryProfsUnavailable[c]);
        }

        /* Class to participants inverse map (automated) */
        Set<Integer>[] salsaVoters = new Set[salsaClasses.length];
        Set<Integer>[] bachataVoters = new Set[bachataClasses.length];
        Set<Integer>[] discoveryVoters = new Set[discoveryClasses.length];
        Set<Integer>[] salsaInterestVoters = new Set[salsaClasses.length];
        Set<Integer>[] bachataInterestVoters = new Set[bachataClasses.length];
        Set<Integer>[] discoveryInterestVoters = new Set[discoveryClasses.length];
        int numParticipants = participantsSalsaChoices.length;
        howManyChoices = new int[numParticipants];
        howManyInterests = new int[numParticipants];
        for (int i = 0; i < salsaVoters.length; ++i) {
            salsaVoters[i] = new HashSet<>();
            salsaInterestVoters[i] = new HashSet<>();
        }
        for (int i = 0; i < bachataVoters.length; ++i) {
            bachataVoters[i] = new HashSet<>();
            bachataInterestVoters[i] = new HashSet<>();
        }
        for (int i = 0; i < discoveryVoters.length; ++i) {
            discoveryVoters[i] = new HashSet<>();
            discoveryInterestVoters[i] = new HashSet<>();
        }

        // assign voters to the classes they voted
        for (int p = 0; p < numParticipants; ++p) {
            for (int c = 0; c < salsaClasses.length; ++c) {
                if (participantsSalsaChoices[p][c] == 2) {
                    ++howManyChoices[p];
                    ++salsaVotes[c];
                    salsaVoters[c].add(p);
                } else if (participantsSalsaChoices[p][c] == 1) {
                    ++howManyInterests[p];
                    ++salsaInterestVotes[c];
                    salsaInterestVoters[c].add(p);
                }
            }
            for (int c = 0; c < bachataClasses.length; ++c) {
                if (participantsBachataChoices[p][c] == 2) {
                    ++howManyChoices[p];
                    ++bachataVotes[c];
                    bachataVoters[c].add(p);
                } else if (participantsBachataChoices[p][c] == 1) {
                    ++howManyInterests[p];
                    ++bachataInterestVotes[c];
                    bachataInterestVoters[c].add(p);
                }
            }
            for (int c = 0; c < discoveryClasses.length; ++c) {
                if (participantsDiscoveryChoices[p][c] == 2) {
                    ++howManyChoices[p];
                    ++discoveryVotes[c];
                    discoveryVoters[c].add(p);
                } else if (participantsDiscoveryChoices[p][c] == 1) {
                    ++howManyInterests[p];
                    ++discoveryInterestVotes[c];
                    discoveryInterestVoters[c].add(p);
                }
            }
        }

        /* Hard constraint: profs cannot teach two classes at the same time (automated) */
        List<Integer>[] sameProfSalsaToBachata = new List[salsaClasses.length];
        List<Integer>[] sameProfSalsaToDiscovery = new List[salsaClasses.length];
        List<Integer>[] sameProfBachataToDiscovery = new List[bachataClasses.length];
        for (int s = 0; s < sameProfSalsaToBachata.length; ++s) {
            sameProfSalsaToBachata[s] = new ArrayList<>();
            sameProfSalsaToDiscovery[s] = new ArrayList<>();
            for (int b = 0; b < bachataProfs.length; ++b) {
                if (salsaProfs[s].stream().anyMatch(bachataProfs[b]::contains)) {
                    sameProfSalsaToBachata[s].add(b);
                }
            }
            for (int d = 0; d < discoveryProfs.length; ++d) {
                if (salsaProfs[s].stream().anyMatch(discoveryProfs[d]::contains)) {
                    sameProfSalsaToDiscovery[s].add(d);
                }
            }
//            System.out.println(sameProfSalsaToBachata[s]); // only one at a time
//            System.out.println(sameProfSalsaToDiscovery[s]); // only one at a time
        }
        for (int b = 0; b < sameProfBachataToDiscovery.length; ++b) {
            sameProfBachataToDiscovery[b] = new ArrayList<>();
            for (int d = 0; d < discoveryProfs.length; ++d) {
                if (bachataProfs[b].stream().anyMatch(discoveryProfs[d]::contains)) {
                    sameProfBachataToDiscovery[b].add(d);
                }
            }
//            System.out.println(sameProfBachataToDiscovery[b]);
        }

        if (salsaClasses.length + bachataClasses.length + discoveryClasses.length != NUM_CLASSROOMS * TIME_SLOTS - 1) { // -1 because of styling class
            throw new RuntimeException("Not Enough classes for the time slots");
        }
        int numClasses = salsaClasses.length + bachataClasses.length + discoveryClasses.length;
        System.out.println("--- Number of classes " + numClasses);


        /* Solve */
        Optimizer optimizer = new Optimizer(salsaVoters, bachataVoters, discoveryVoters, salsaInterestVoters, bachataInterestVoters, discoveryInterestVoters,
                duties, salsaProfs, bachataProfs, discoveryProfs,
                sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery,
                salsaProfsUnavailable, bachataProfsUnavailable, discoveryProfsUnavailable);
        float bestScore = optimizer.solve(0, (1 << numClasses) - 1, true);
        System.out.println("--- Average Loss " + (bestScore / TIME_SLOTS));
//        System.out.println("--- with the current weight function this means how many classes missed per hour --");
        System.out.println();

        if (bestScore > SCORE_LIMIT) {
            System.out.println("Impossible !!");
            return;
        }

        /* Visualize */
        System.out.println("================== Weights ======================");
        for (int p = 0; p < participants.length; ++p) {
            System.out.println(participants[p] + " (" + howManyChoices[p] + ", " + howManyInterests[p] + "): " + optimizer.weights[p] + ", " + optimizer.interestWeights[p]);
        }
        for (int i = 0, classesSubset = (1 << numClasses) - 1; i < TIME_SLOTS; ++i) {
            BestGuess bestGuess = optimizer.memo.get(classesSubset);
            System.out.print(printA(bestGuess));
            System.out.print(printB(bestGuess));
            System.out.print(printC(bestGuess));
            System.out.println(optimizer.computeSimilarity(bestGuess) + "%");
            int salsaOffset = bachataVotes.length + discoveryVotes.length;
            int bachataOffset = discoveryVotes.length;
            classesSubset -= 1 << (salsaOffset + bestGuess.timeSlot[CLASSROOM_ONE_ID]);
            if (!bestGuess.extraDiscovery) {
                classesSubset -= 1 << (bachataOffset + bestGuess.timeSlot[CLASSROOM_TWO_ID]);
            }
            classesSubset -= 1 << (bestGuess.timeSlot[CLASSROOM_THREE_ID]);
        }

        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();
        int[] missingPreferences = new int[participants.length];
        for (int i = 0, classesSubset = (1 << numClasses) - 1; i < TIME_SLOTS; ++i) {
            BestGuess bestGuess = optimizer.memo.get(classesSubset);
            showResultPerPerson(bestGuess, salsaVoters, bachataVoters, discoveryVoters, salsaProfs, bachataProfs, discoveryProfs, duties, i, missingPreferences);
            int salsaOffset = bachataVotes.length + discoveryVotes.length;
            int bachataOffset = discoveryVotes.length;
            classesSubset -= 1 << (salsaOffset + bestGuess.timeSlot[CLASSROOM_ONE_ID]);
            if (!bestGuess.extraDiscovery) {
                classesSubset -= 1 << (bachataOffset + bestGuess.timeSlot[CLASSROOM_TWO_ID]);
            }
            classesSubset -= 1 << (bestGuess.timeSlot[CLASSROOM_THREE_ID]);
        }
        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();

        System.out.println("People that will have to do less than what they chose");
        Participant[] result = new Participant[participants.length];
        for (int p = 0; p < participants.length; ++p) {
            result[p] = new Participant();
            result[p].name = participants[p];
            result[p].numPreferences = howManyChoices[p];
            result[p].missingPreferences = missingPreferences[p];
            result[p].score = missingPreferences[p] / (float) result[p].numPreferences;
        }
        Arrays.sort(result);
        for (Participant participant : result) {
            if (participant.numPreferences - participant.missingPreferences >= TIME_SLOTS || participant.missingPreferences == 0) {
                continue;
            }
            System.out.println(participant.name + "(" + participant.numPreferences + "): " + (participant.numPreferences - participant.missingPreferences));
        }
    }

    static class Participant implements Comparable<Participant> {
        int numPreferences, missingPreferences;
        float score;
        String name;

        @Override
        public int compareTo(Participant o) {
            return Float.compare(o.score, this.score);
        }
    }

    static void showResultPerPerson(BestGuess bestGuess, Set<Integer>[] salsa, Set<Integer>[] bachata, Set<Integer>[] discovery,
                                    List<Integer>[] salsaProfs, List<Integer>[] bachataProfs, List<Integer>[] discoveryProfs,
                                    Set<Integer>[] duties, int i, int[] missingPreferences) {
        Set<Integer> interestedInSalsa = salsa[bestGuess.timeSlot[CLASSROOM_ONE_ID]];
        Set<Integer> interestedInBachata = bestGuess.extraDiscovery ? new HashSet<>() : bachata[bestGuess.timeSlot[CLASSROOM_TWO_ID]];
        Set<Integer> interestedInThirdClass =  discovery[bestGuess.timeSlot[CLASSROOM_THREE_ID]];
        Set<Integer> interestedInSB = new HashSet<>(interestedInSalsa), interestedInST = new HashSet<>(interestedInSalsa), interestedInBT = new HashSet<>(interestedInBachata);
        interestedInSB.retainAll(interestedInBachata);
        interestedInST.retainAll(interestedInThirdClass);
        interestedInBT.retainAll(interestedInThirdClass);
        Set<Integer> interestedInExactlyThree = new HashSet<>(interestedInSalsa);
        interestedInExactlyThree.retainAll(interestedInBT);
        // Note : no need to remove SBT because it's included in SB, ST, BT
        Set<Integer> interestedInExactlyOne = new HashSet<>(interestedInSalsa);
        interestedInExactlyOne.addAll(interestedInBachata);
        interestedInExactlyOne.addAll(interestedInThirdClass);
        interestedInExactlyOne.removeAll(interestedInSB);
        interestedInExactlyOne.removeAll(interestedInST);
        interestedInExactlyOne.removeAll(interestedInBT);
        Set<Integer> interestedInExactlyTwo = new HashSet<>(interestedInSB);
        interestedInExactlyTwo.addAll(interestedInST);
        interestedInExactlyTwo.addAll(interestedInBT);
        interestedInExactlyTwo.removeAll(interestedInExactlyThree);
        Set<Integer> onDuty = new HashSet<>(duties[i]);
        /* Count profs as on duty, because they have no option just like the people in the kitchen */
        onDuty.addAll(salsaProfs[bestGuess.timeSlot[CLASSROOM_ONE_ID]]);
        onDuty.addAll(bestGuess.extraDiscovery ? new ArrayList<>() : bachataProfs[bestGuess.timeSlot[CLASSROOM_TWO_ID]]);
        onDuty.addAll(discoveryProfs[bestGuess.timeSlot[CLASSROOM_THREE_ID]]);

        Set<Integer> interestedInExactlyOneAndOnDuty = new HashSet<>(interestedInExactlyOne);
        interestedInExactlyOneAndOnDuty.retainAll(onDuty);
        Set<Integer> interestedInExactlyTwoAndOnDuty = new HashSet<>(interestedInExactlyTwo);
        interestedInExactlyTwoAndOnDuty.retainAll(onDuty);
        Set<Integer> interestedInExactlyThreeAndOnDuty = new HashSet<>(interestedInExactlyThree);
        interestedInExactlyThreeAndOnDuty.retainAll(onDuty);
        Set<Integer> interestedInExactlyTwoAndNotOnDuty = new HashSet<>(interestedInExactlyTwo);
        interestedInExactlyTwoAndNotOnDuty.removeAll(interestedInExactlyTwoAndOnDuty);
        Set<Integer> interestedInExactlyThreeAndNotOnDuty = new HashSet<>(interestedInExactlyThree);
        interestedInExactlyThreeAndNotOnDuty.removeAll(interestedInExactlyThreeAndOnDuty);

        System.out.println("WORKSHOP " + (i + 1) + ": List of people not on duty that will have to choose between two classes: " + interestedInExactlyTwoAndNotOnDuty.size());
        for (int p : interestedInExactlyTwoAndNotOnDuty) {
            System.out.print(participants[p].trim() + "(" + howManyChoices[p] + "), ");
            missingPreferences[p]++;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people not on duty that will have to choose between three classes: " + interestedInExactlyThreeAndNotOnDuty.size());
        for (int p : interestedInExactlyThreeAndNotOnDuty) {
            System.out.print(participants[p].trim() + "(" + howManyChoices[p] + "), ");
            missingPreferences[p] += 2;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty that miss one class they want to do: " + interestedInExactlyOneAndOnDuty.size());
        for (int p : interestedInExactlyOneAndOnDuty) {
            System.out.print(participants[p].trim() + "(" + howManyChoices[p] + "), ");
            missingPreferences[p]++;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty missing two classes they want to do: " + interestedInExactlyTwoAndOnDuty.size());
        for (int p : interestedInExactlyTwoAndOnDuty) {
            System.out.print(participants[p].trim() + "(" + howManyChoices[p] + "), ");
            missingPreferences[p] += 2;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty missing three classes they want to do: " + interestedInExactlyThreeAndOnDuty.size());
        for (int p : interestedInExactlyThreeAndOnDuty) {
            System.out.print(participants[p].trim() + "(" + howManyChoices[p] + "), ");
            missingPreferences[p] += 3;
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    static String printA(BestGuess bestGuess) {
        return salsaClasses[bestGuess.timeSlot[CLASSROOM_ONE_ID]] + "(" + salsaVotes[bestGuess.timeSlot[CLASSROOM_ONE_ID]] + ", "
                + salsaInterestVotes[bestGuess.timeSlot[CLASSROOM_ONE_ID]] + ", " + (salsaVotes[bestGuess.timeSlot[CLASSROOM_ONE_ID]]
                + INTEREST_WEIGHT * salsaInterestVotes[bestGuess.timeSlot[CLASSROOM_ONE_ID]]) + ")" + "\t\t";
    }

    static String printB(BestGuess bestGuess) {
        if (bestGuess.extraDiscovery) {
            return "";
        }
        return bachataClasses[bestGuess.timeSlot[CLASSROOM_TWO_ID]] + "(" + bachataVotes[bestGuess.timeSlot[CLASSROOM_TWO_ID]] + ", "
                + bachataInterestVotes[bestGuess.timeSlot[CLASSROOM_TWO_ID]] + ", " + (bachataVotes[bestGuess.timeSlot[CLASSROOM_TWO_ID]]
                + INTEREST_WEIGHT * bachataInterestVotes[bestGuess.timeSlot[CLASSROOM_TWO_ID]]) + ")" + "\t\t";
    }

    static String printC(BestGuess bestGuess) {
        return discoveryClasses[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + "(" + discoveryVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + ", "
                + discoveryInterestVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + ", " + (discoveryVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]]
                + INTEREST_WEIGHT * discoveryInterestVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]]) + ")" + "\t\t";

    }

    private static class Optimizer {
        public static final int YOS_1 = 5;
        public static final int HIPHOP = 0;
        private static final int AFRORUMBA = 0;
        Map<Integer, BestGuess> memo = new HashMap<>();
        Set<Integer>[] salsaVoters, bachataVoters, discoveryVoters, salsaInterestVoters, bachataInterestVoters, discoveryInterestVoters, duties;
        List<Integer>[] sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery;
        Set<Integer>[] salsaProfsUnavailable, bachataProfsUnavailable, discoveryProfsUnavailable;

        List<Integer>[] salsaProfs, bachataProfs, discoveryProfs;
        final BestGuess IMPOSSIBLE = new BestGuess(INFINITY);
        float[] weights, interestWeights;

        final float heuristicTarget;

        Optimizer(Set<Integer>[] salsaVoters, Set<Integer>[] bachataVoters, Set<Integer>[] discoveryVoters,
                  Set<Integer>[] salsaInterestVoters, Set<Integer>[] bachataInterestVoters, Set<Integer>[] discoveryInterestVoters,
                  Set<Integer>[] duties,
                  List<Integer>[] salsaProfs,
                  List<Integer>[] bachataProfs,
                  List<Integer>[] discoveryProfs,
                  List<Integer>[] sameProfSalsaToBachata,
                  List<Integer>[] sameProfSalsaToDiscovery, List<Integer>[] sameProfBachataToDiscovery,
                  Set<Integer>[] salsaProfsUnavailable, Set<Integer>[] bachataProfsUnavailable, Set<Integer>[] discoveryProfsUnavailable) {
            this.salsaVoters = salsaVoters;
            this.bachataVoters = bachataVoters;
            this.discoveryVoters = discoveryVoters;
            this.salsaInterestVoters = salsaInterestVoters;
            this.bachataInterestVoters = bachataInterestVoters;
            this.discoveryInterestVoters = discoveryInterestVoters;
            this.duties = duties;
            this.salsaProfs = salsaProfs;
            this.bachataProfs = bachataProfs;
            this.discoveryProfs = discoveryProfs;
            this.sameProfSalsaToBachata = sameProfSalsaToBachata;
            this.sameProfSalsaToDiscovery = sameProfSalsaToDiscovery;
            this.sameProfBachataToDiscovery = sameProfBachataToDiscovery;
            this.salsaProfsUnavailable = salsaProfsUnavailable;
            this.bachataProfsUnavailable = bachataProfsUnavailable;
            this.discoveryProfsUnavailable = discoveryProfsUnavailable;
            weights = new float[howManyChoices.length];
            interestWeights = new float[howManyInterests.length];
            for (int p = 0; p < howManyChoices.length; ++p) {
                // method 1 : linear, high penalties for low choices
                int CUTOFF = TIME_SLOTS + 1;
                int MAX_COUNT = 14; //TIME_SLOTS * NUM_CLASSROOMS;
                float m = -1.0f / (MAX_COUNT - TIME_SLOTS);
                float q = 1.0f + TIME_SLOTS / (float) (MAX_COUNT - TIME_SLOTS);
                weights[p] = howManyChoices[p] < CUTOFF ? (CUTOFF - howManyChoices[p]) : (m * howManyChoices[p] + q);
                interestWeights[p] = INTEREST_WEIGHT * weights[p];
                // method 2: hyperbolic, proportional so missing 1 / 2 is the same as 3.5 / 7, interests are 0.4 of one vote
//                weights[p] = howManyChoices[p] == 0 ? 0.0f : (TIME_SLOTS / (float) howManyChoices[p]);
//                interestWeights[p] = howManyChoices[p] == 0 ? 0.0f : (0.4f * TIME_SLOTS / (float) howManyChoices[p] / howManyInterests[p]);
                // method 3: wx + 0.4wy = 7; w(x + 0.4y) = 7; w = 7/(x + 0.4y)
//                weights[p] = howManyChoices[p] == 0 ? 0.0f : (TIME_SLOTS / (howManyChoices[p] + INTEREST_WEIGHT * howManyInterests[p]));
//                interestWeights[p] = howManyChoices[p] == 0 ? 0.0f : (INTEREST_WEIGHT * weights[p]);
            }
            this.heuristicTarget = (float) (Arrays.stream(salsaVotes).sum() + Arrays.stream(bachataVotes).sum() + Arrays.stream(discoveryVotes).sum())
                    / (salsaVotes.length + bachataVotes.length + discoveryVotes.length);
            initLossLookup();
            System.out.println("--- Average " + heuristicTarget);
        }

        float solve(int timeSlotId, int classesSubset, boolean extraDiscovery) {
            if (timeSlotId >= TIME_SLOTS) { // Base case
                boolean allClassesOnSchedule = classesSubset == 0;
                return allClassesOnSchedule ? 0.0f : INFINITY;
            }
            BestGuess ans = memo.get(classesSubset);
            if (ans != null) {
                return ans.score;
            }
            ans = guessSalsa(timeSlotId, classesSubset, extraDiscovery, new int[NUM_CLASSROOMS]);
            memo.put(classesSubset, ans);
            return ans.score;
        }

        BestGuess guessSalsa(int timeSlotId, int classesSubset, boolean extraDiscovery, int[] timeSlot) {
            BestGuess bestGuess = IMPOSSIBLE;
            int offset = bachataVoters.length + discoveryVoters.length;
            for (int currClass = 0; currClass < salsaVoters.length; ++currClass) {
                if (isProfUnavailable(currClass, timeSlotId, salsaProfsUnavailable)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked = (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_ONE_ID] = currClass;
                    BestGuess someGuess = guessBachata(timeSlotId, classesSubset - classMask, extraDiscovery, timeSlot);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_ONE_ID] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess guessBachata(int timeSlotId, int classesSubset, boolean extraDiscovery, int[] timeSlot) {
            BestGuess bestGuess = IMPOSSIBLE;
            if (extraDiscovery && !isProfAlreadyInTimeSlot(STYLING_ID, sameProfSalsaToDiscovery, timeSlot[CLASSROOM_ONE_ID])
                    && !isProfUnavailable(STYLING_ID, timeSlotId, discoveryProfsUnavailable)) {
                int offset = 0;
                int classMask = 1 << (offset + STYLING_ID);
                timeSlot[CLASSROOM_TWO_ID] = -1;
                timeSlot[CLASSROOM_THREE_ID] = STYLING_ID;
                BestGuess someGuess = endOfTimeSlot(timeSlotId, classesSubset - classMask, false, true, timeSlot);
                if (someGuess.score < bestGuess.score) {
                    someGuess.timeSlot[CLASSROOM_TWO_ID] = -1;
                    someGuess.timeSlot[CLASSROOM_THREE_ID] = STYLING_ID;
                    someGuess.extraDiscovery = true;
                    bestGuess = someGuess;
                }
            }
            int offset = discoveryVoters.length;
            for (int currClass = 0; currClass < bachataVoters.length; ++currClass) {
                if (isProfAlreadyInTimeSlot(currClass, sameProfSalsaToBachata, timeSlot[CLASSROOM_ONE_ID])
                        || isProfUnavailable(currClass, timeSlotId, bachataProfsUnavailable)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked = (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_TWO_ID] = currClass;
                    BestGuess someGuess = guessDiscovery(timeSlotId, classesSubset - classMask, extraDiscovery, timeSlot);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_TWO_ID] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess guessDiscovery(int timeSlotId, int classesSubset, boolean extraDiscovery, int[] timeSlot) {
            BestGuess bestGuess = IMPOSSIBLE;
            int offset = 0;
            int numDiscoveries = discoveryVoters.length - 1; // -1 because the styling class is handled as a special case in guessBachata
            for (int currClass = 0; currClass < numDiscoveries; ++currClass) {
                if (isProfAlreadyInTimeSlot(currClass, sameProfSalsaToDiscovery, timeSlot[CLASSROOM_ONE_ID])
                        || isProfAlreadyInTimeSlot(currClass, sameProfBachataToDiscovery, timeSlot[CLASSROOM_TWO_ID])
                        || isProfUnavailable(currClass, timeSlotId, discoveryProfsUnavailable)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked = (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_THREE_ID] = currClass;
                    BestGuess someGuess = endOfTimeSlot(timeSlotId, classesSubset - classMask, extraDiscovery, false, timeSlot);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_THREE_ID] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess endOfTimeSlot(int timeSlotId, int classesSubset, boolean extraDiscovery, boolean hasStyling, int[] timeSlot) {
            float lookup = solve(timeSlotId + 1, classesSubset, extraDiscovery);
            float loss = computeLoss(timeSlotId, classesSubset, timeSlot, hasStyling);
            return lookup > SCORE_LIMIT ? IMPOSSIBLE : new BestGuess(loss + lookup);
        }

        boolean isProfAlreadyInTimeSlot(int b, List<Integer>[] sameProfAtoB, int a) {
            return sameProfAtoB[a].stream().anyMatch(otherClassTheyGive -> b == otherClassTheyGive);
        }

        boolean isProfUnavailable(int currClass, int timeSlotId, Set<Integer>[] profsOnDuty) {
            return profsOnDuty[currClass].contains(timeSlotId);
        }

        private float computeAverage(int[] timeSlot, int[] aVotes, int[] bVotes, int[] cVotes, boolean extraDiscovery) {
            if (extraDiscovery) {
                float count = 2.0f;
                return (aVotes[timeSlot[CLASSROOM_ONE_ID]] + cVotes[timeSlot[CLASSROOM_THREE_ID]]) / count;
            }
            float count = 3.0f;
            return (aVotes[timeSlot[CLASSROOM_ONE_ID]] + bVotes[timeSlot[CLASSROOM_TWO_ID]] + cVotes[timeSlot[CLASSROOM_THREE_ID]]) / count;
        }

        float weightedSum(Set<Integer> A, float[] w) {
            return A.stream().map(p -> w[p]).reduce(0.0f, Float::sum);
        }

        float[][][][] penalty;

        // implements loss = |A| + |B| + |C| - |AuBuC| + |D(AuBuC)|
        void initLossLookup() {
            System.out.println("--------- Init penalties ------------");
            penalty = new float[TIME_SLOTS][salsaVoters.length][bachataVoters.length][discoveryVoters.length];
            for (int s = 0; s < salsaVoters.length; ++s) {
                for (int b = 0; b < bachataVoters.length; ++b) {
                    for (int d = 0; d < discoveryVoters.length; ++d) {
                        Set<Integer> secondClassVoters = bachataVoters[b];
                        Set<Integer> secondClassInterestVoters = bachataInterestVoters[b];
                        List<Integer> secondClassProfs = bachataProfs[b];
                        if (d == STYLING_ID) {
                            if (b == 0) {                                // we have to store the styling class somewhere so we store it on b = 0, other values are unused
                                // bachata class is used for styling
                                secondClassVoters = new HashSet<>();
                                secondClassInterestVoters = new HashSet<>();
                                secondClassProfs = Collections.emptyList();
                            } else {
                                continue;
                            }
                        }
                        Set<Integer> AuBuC = new HashSet<>();
                        AuBuC.addAll(salsaVoters[s]);
                        AuBuC.addAll(secondClassVoters);
                        AuBuC.addAll(discoveryVoters[d]);
                        Set<Integer> AuBuCInterest = new HashSet<>();
                        AuBuCInterest.addAll(salsaInterestVoters[s]);
                        AuBuCInterest.addAll(secondClassInterestVoters);
                        AuBuCInterest.addAll(discoveryInterestVoters[d]);
                        for (int i = 0; i < TIME_SLOTS; ++i) {
                            Set<Integer> DAuBuC = new HashSet<>(duties[i]);
                            /* Count profs as on duty, because they have no option just like the people in the kitchen */
                            DAuBuC.addAll(salsaProfs[s]);
                            DAuBuC.addAll(secondClassProfs);
                            DAuBuC.addAll(discoveryProfs[d]);
                            Set<Integer> DAuBuCInterest = new HashSet<>(DAuBuC); // like duties[i] but with profs
                            DAuBuC.retainAll(AuBuC);
                            DAuBuCInterest.retainAll(AuBuCInterest);
                            penalty[i][s][b][d] = weightedSum(salsaVoters[s], weights)
                                    + weightedSum(secondClassVoters, weights)
                                    + weightedSum(discoveryVoters[d], weights)
                                    - weightedSum(AuBuC, weights) + weightedSum(DAuBuC, weights);
                            penalty[i][s][b][d] += weightedSum(salsaInterestVoters[s], interestWeights)
                                    + weightedSum(secondClassInterestVoters, interestWeights)
                                    + weightedSum(discoveryInterestVoters[d], interestWeights)
                                    - weightedSum(AuBuCInterest, interestWeights) + weightedSum(DAuBuCInterest, interestWeights);
                        }
                    }
                }
            }

            System.out.println("--------- Init penalties done ------------");

        }

        float computeLoss(int timeSlotId, int classesSubset, int[] timeSlot, boolean hasStyling) {
            float loss = penalty[timeSlotId][timeSlot[CLASSROOM_ONE_ID]]
                    [hasStyling ? 0 : timeSlot[CLASSROOM_TWO_ID]] // 0 by convention stores the styling losses
                    [timeSlot[CLASSROOM_THREE_ID]];
            if (hasStyling && timeSlot[CLASSROOM_THREE_ID] != STYLING_ID) {
                throw new RuntimeException("Sanity check failed");
            }
            //Boris only on Saturday until 4pm
            if (timeSlot[CLASSROOM_TWO_ID] == BORIS_CLASS_1 ||  timeSlot[CLASSROOM_TWO_ID] == BORIS_CLASS_2) {
                if (timeSlotId > SATURDAY_MORNING) {
                    loss += 10000000;
                }
            }
            //Teya can’t teach Saturday morning
            if (timeSlot[CLASSROOM_TWO_ID] == TEYA_CLASS) {
                if (timeSlotId <= SATURDAY_MORNING) {
                    loss += 10000000;
                }
            }
            if (timeSlotId == AFTER_SATURDAY_LUNCH && timeSlot[CLASSROOM_THREE_ID] == AERIAL_LIFT_CLASS) {
                loss += 10000000;
            }
            if (timeSlotId == AFTER_SATURDAY_LUNCH && timeSlot[CLASSROOM_ONE_ID] == CALENA) {
                loss += 10000000;
            }

            // Virginia has to leave sooner
//            if (!hasStyling && timeSlot[CLASSROOM_THREE_ID] == SAMBA && timeSlotId != FIRST_CLASS) {
//                loss += 10000000;
//            }
//
//            if (!hasStyling && timeSlot[CLASSROOM_ONE_ID] == SKANDER_MUSIC && timeSlot[CLASSROOM_THREE_ID] == HIPHOP) {
//                loss += 10000000;
//            }
//
//            // put history with PW
//            boolean isHistory = timeSlot[CLASSROOM_ONE_ID] == STYLING_HISTORY_ID || (hasStyling && timeSlot[CLASSROOM_THREE_ID] == STYLING_HISTORY_ID);
//            boolean isPartnerwork = timeSlot[CLASSROOM_ONE_ID] == VALENTIN_PW || (hasStyling && timeSlot[CLASSROOM_THREE_ID] == VALENTIN_PW)
//                    || timeSlot[CLASSROOM_ONE_ID] == MARLA_PW || (hasStyling && timeSlot[CLASSROOM_THREE_ID] == MARLA_PW)
//                    || timeSlot[CLASSROOM_ONE_ID] == KAI_PW || (hasStyling && timeSlot[CLASSROOM_THREE_ID] == KAI_PW);
//            if (isHistory && !isPartnerwork) {
//                loss += 10000000;
//            }
//            // put bla bla when people can rest
//            if (isHistory && timeSlotId < AFTER_SATURDAY_LUNCH) {
//                loss += 10000000;
//            }

            // at least one intermediate
            int s = timeSlot[CLASSROOM_ONE_ID];
            boolean isSalsaInter = interSalsa.contains(s);
            boolean isBachataInter = interBachata.contains(timeSlot[CLASSROOM_TWO_ID]);
            if (!isSalsaInter && !isBachataInter) {
                loss += 10000000;
            }

            /* Hyperparameters for soft constraints*/
            /* Below are some examples that are reasonable to fine tune happiness of the people attending the workshops.
                Note: in practice the weights used are irrelevant as all constraints could be satisfied
              if (isBothSensual) {
                  meanAbsoluteError += Integer.MAX_VALUE;
              }
              if (isPAPartnerwork && isRetoPartnerwork) {
                  meanAbsoluteError += 800.0f;
              }
              // ensure that difficulty is alternating across time slots
              if (timeSlot[EXTRA_SALSA_TYPE_ID] < 0 &&
                      timeSlot[EXTRA_BACHATA_TYPE_ID] < 0 &&
                      (isTimeAdvanced && salsaClassLevels[timeSlot[SALSA_TYPE_ID]] == ClassLevel.INTERMEDIATE)
                  ||  (isTimeIntermediate && salsaClassLevels[timeSlot[SALSA_TYPE_ID]] == ClassLevel.ADVANCED)) {
                  meanAbsoluteError += 1000.0f;
              }
              if (isTimeAfterLunch && (isCalena || isHipHop) ) {
                  meanAbsoluteError += Integer.MAX_VALUE;
              }
            */
            return loss;
        }

        float computeSimilarity(BestGuess bestGuess) {
            return computeAverage(bestGuess.timeSlot, salsaVotes, bachataVotes, discoveryVotes, bestGuess.extraDiscovery) / heuristicTarget * 100.0f;
        }
    }

    enum ClassLevel {
        INTERMEDIATE,
        ADVANCED
    }

    private static class BestGuess {
        float score;
        int[] timeSlot;
        boolean extraDiscovery;

        BestGuess(float score) {
            this.score = score;
            timeSlot = new int[NUM_CLASSROOMS];
            for (int i = 0; i < NUM_CLASSROOMS; ++i) {
                timeSlot[i] = -1;
            }
        }
    }
}
