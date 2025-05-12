import java.util.ArrayList;
import java.util.Arrays;
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
    public static final int SAMBA = 3;
    public static final int LAST_SATURDAY_TIMESLOT = 4;
    public static final int YOS_2 = 6;
    public static final int CALENA = 5;
    public static final int AFTER_SATURDAY_LUNCH = 2;
    public static final float INTEREST_WEIGHT = 0.2f;
    public static final int STYLING_HISTORY_ID = 5; // styling has been replaced with history class
    public static final int REGGAE = 1;
    public static final int URBAN_SALSA = 1;
    public static final int SKANDER_MUSIC = 2;
    public static final int SALSA_FUSION = 4;
    public static final int STYLING_TIME = 4;
    public static final int VALENTIN_PW = 3;
    public static final int MARLA_PW = 6;
    public static final int KAI_PW = 7;
    public static final int NOT_INTERESTED = 0;
    public static final int I_DONT_WANNA_MISS_IT = 2;
    public static final int INTERESTED = 1;
    public static final int ILA = 11;
    public static final int LISON = 15;
    public static final int TEYA = 55;
    public static final int BORIS = 46;
    public static final int YOS = 58;
    public static final int JULIE = 4;
    public static final int ROBIN = 45;
    public static final int MATTHIEU = 50;
    public static final int PA = 2;
    public static final int ESTE = 34;
    public static final int LUCA = 14;
    public static final int VALENTIN = 49;
    public static final int FIRST_CLASS = 0;

    /* Dataset */

    static List<Integer> interSalsa = Arrays.asList(2,3,6,7,STYLING_HISTORY_ID);
    static String[] salsaClasses = new String[]{
            "Salsa pasitos afro-rumba (Advanced)  —  Ila",
            "Salsa urban style (Advanced) — PA y Ila",
            "Salsa musicality: catching breaks (Intermediate) — Skander y Maja",
            "Salsa partnerwork (Intermediate) — Valentin y Lison",
            "Salsa fusion (Advanced) — PA y Este",
            "Salsa history (All levels)", // "Salsa men vs lady styling battle (Advanced) — PA y Este", <- removed from the program
            "Salsa leading and following (Intermediate) — Robin y Marla",
            "Salsa partnerwork (Intermediate) — Robin y Kai"
    };
    static List<Integer> interBachata = Arrays.asList(1,2,4,5);
    static String[] bachataClasses = new String[]{
            "Bachata fusion (Advanced) — Lison y Patrick",
            "Bachata musicality parterwork (Intermediate) — Patrick",
            "Bachata sensual 1 (Intermediate) — Boris y Teya",
            "Bachata sensual 2 (Advanced) — Boris y Teya",
            "Bachata footwork (Intermediate) — Yoss",
            "Bachata moderna (Intermediate) — Yoss y Julie",
            "Bachata sensual connection (Advanced) — Yoss y Lison"
    };
    static String[] discoveryClasses = new String[]{
            "Hip hop (All levels) — Ila",
            "Reggaeton (All levels) — Ila",
            "Jive (All levels) — Lison y Luca",
            "Samba (All levels) — Virginia",
            "Cha-Cha-Cha (All levels) — Heidi y Dennis",
            "Salsa caleña (All levels) — Luca"
    };
    static int PATRICK = 41;
        static String[] participants = {
            "Adrian Pfiffner",
            "Marc Blöchlinger",
            "P.A.",
            "Léa Consuegra",
            "Julie Hernandez",
            "Mariana Leon",
            "Laurine Gasser",
            "Laurent Bugnard",
            "Noa Varela Cinquegrani",
            "Milena Schuhmacher",
            "Annamira O'Toole",
            "Ilaria Ricchi",
            "Kaisu Hiltunen",
            "Kai Ott",
            "Ruef Luca",
            "Lison Ravassard",
            "Larissa Klose",
            "Jose Antonio Simon Greminger",
            "Ivan Zivadinovic",
            "Skander Moalla",
            "Richard Santiago",
            "Michele Pettinato",
            "Yannick",
            "Jusef Akbari",
            "Chloé Monin",
            "Fabian Weber",
            "Elena Yfantis",
            "David Barioni",
            "Sabrina Kleesattel",
            "Carolin Giel",
            "Catarina Meier",
            "Maja Stamenkovic",
            "Alberto Zirondelli",
            "Céline Wilhelm",
            "Estefania Arroyo",
            "Daniela Correa Orozco",
            "Merel Kuijs",
            "Diego Clavijo",
            "Igor Krawczuk",
            "Heidi Lee",
            "Tristan Reinhard",
            "Patrick Sgrò",
            "Niál Perry",
            "Yosri Mami",
            "Mélanie Kugler",
            "Robin Rahel Renggli",
            "Boris Bergsma",
            "Helen Risch",
            "Marla Kohli",
            "Valentin Gobert",
            "Matthieu",
            "Natali Gomez",
            "Sandra Valero Cardoso",
            "Morgana Grillo", // not coming
            "Dennis Makarov",
            "Teya Petrova",
            "Alic Kaufmann",
            "Claudia La Valle",
            "Yoss",
            "Florian",
            "Ulysse",
            "NA",
    };


    static int[][] participantsSalsaChoices = {
            {2, 2, 2, 1, 1, 1, 1, 1},
            {0, 0, 2, 2, 0, 1, 2, 2},
            {1, 2, 0, 0, 2, 2, 0, 0},
            {2, 2, 2, 1, 2, 0, 1, 1},
            {0, 0, 1, 1, 1, 1, 1, 1},
            {2, 0, 2, 2, 1, 2, 1, 1},
            {0, 0, 1, 2, 0, 0, 1, 1},
            {2, 1, 2, 2, 0, 1, 1, 1},
            {1, 1, 1, 0, 2, 2, 0, 0},
            {2, 2, 1, 1, 1, 1, 1, 1},
            {0, 0, 2, 2, 0, 1, 2, 2},
            {2, 2, 1, 0, 2, 1, 0, 0},
            {0, 1, 1, 2, 1, 2, 1, 1},
            {2, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 2, 1, 2, 0, 1},
            {0, 0, 0, 2, 1, 2, 0, 0},
            {2, 0, 2, 1, 0, 0, 0, 1},
            {2, 0, 2, 1, 1, 2, 0, 0},
            {0, 0, 2, 2, 0, 1, 2, 2},
            {2, 1, 2, 1, 1, 2, 1, 1},
            {2, 1, 2, 2, 1, 1, 2, 2},
            {2, 2, 2, 0, 1, 2, 0, 0},
            {2, 0, 2, 1, 0, 1, 1, 1},
            {0, 0, 0, 1, 2, 0, 1, 2},
            {1, 0, 1, 1, 2, 2, 0, 1},
            {1, 0, 2, 0, 2, 0, 1, 2},
            {0, 0, 0, 1, 2, 0, 1, 2},
            {0, 1, 0, 1, 1, 2, 1, 1},
            {0, 0, 0, 1, 2, 0, 1, 2},
            {1, 1, 2, 2, 1, 1, 2, 1},
            {2, 2, 1, 1, 2, 1, 0, 0},
            {2, 2, 2, 1, 1, 1, 1, 1},
            {0, 0, 2, 2, 2, 1, 2, 2},
            {0, 0, 2, 2, 2, 1, 2, 2},
            {2, 2, 1, 0, 2, 2, 0, 0},
            {2, 2, 1, 0, 0, 1, 1, 1},
            {1, 1, 2, 1, 1, 0, 1, 2},
            {2, 1, 2, 2, 2, 1, 2, 2},
            {2, 2, 2, 1, 0, 0, 1, 0},
            {1, 1, 1, 1, 2, 2, 1, 1},
            {0, 0, 1, 2, 1, 0, 1, 2},
            {1, 2, 1, 0, 2, 0, 0, 0},
            {0, 0, 1, 2, 1, 1, 2, 2},
            {2, 0, 0, 1, 0, 1, 0, 0},
            {0, 0, 2, 2, 0, 0, 0, 1},
            {2, 2, 0, 0, 1, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 1, 1},
            {2, 2, 2, 1, 1, 1, 1, 1},
            {2, 1, 2, 0, 1, 1, 2, 1},
            {1, 2, 1, 2, 2, 0, 1, 1},
            {2, 1, 2, 1, 1, 1, 1, 1},
            {2, 0, 2, 1, 1, 1, 1, 1},
            {0, 0, 2, 2, 0, 1, 2, 2},
            {0, 0, 0, 0, 0, 0, 0, 0}, //{0, 0, 1, 0, 0, 0, 1, 1},
            {0, 1, 2, 1, 1, 2, 2, 2},
            {2, 1, 2, 1, 2, 2, 1, 1},
            {0, 0, 2, 0, 0, 0, 0, 0},
            {1, 1, 1, 2, 1, 2, 1, 2},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0},
};
    static int[][] participantsBachataChoices = {
            {2, 1, 2, 1, 1, 1, 0},
            {0, 1, 1, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 2, 0, 0},
            {2, 1, 1, 1, 2, 2, 0},
            {1, 0, 2, 0, 1, 1, 0},
            {1, 2, 2, 0, 2, 1, 0},
            {0, 1, 2, 1, 2, 2, 0},
            {1, 2, 2, 1, 0, 1, 0},
            {1, 2, 0, 1, 2, 2, 0},
            {0, 2, 0, 0, 2, 1, 0},
            {1, 1, 0, 0, 0, 0, 0},
            {1, 1, 2, 2, 2, 2, 0},
            {2, 1, 2, 2, 1, 0, 0},
            {1, 1, 1, 1, 1, 1, 0},
            {2, 1, 1, 2, 0, 1, 0},
            {1, 2, 0, 0, 2, 0, 0},
            {1, 2, 2, 1, 2, 1, 0},
            {0, 0, 1, 0, 0, 0, 0},
            {0, 2, 2, 0, 1, 1, 0},
            {0, 0, 0, 0, 1, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 2, 1, 0, 2, 0, 0},
            {2, 1, 2, 2, 1, 1, 0},
            {1, 1, 1, 2, 2, 1, 0},
            {0, 1, 2, 2, 1, 0, 0},
            {2, 1, 2, 2, 1, 1, 0},
            {0, 1, 1, 0, 1, 1, 0},
            {2, 1, 2, 2, 1, 1, 0},
            {1, 2, 2, 1, 2, 1, 0},
            {2, 1, 1, 1, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 2, 2, 0, 1, 1, 0},
            {0, 2, 2, 0, 1, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {0, 1, 2, 1, 1, 1, 1},
            {0, 1, 2, 2, 2, 1, 1},
            {0, 0, 2, 0, 0, 2, 1},
            {0, 2, 2, 0, 2, 1, 1},
            {1, 1, 2, 1, 0, 2, 1},
            {2, 2, 2, 2, 1, 1, 2},
            {2, 2, 0, 0, 1, 0, 1},
            {1, 1, 0, 2, 2, 2, 2},
            {1, 1, 1, 2, 2, 2, 2},
            {0, 1, 2, 0, 0, 1, 0},
            {1, 0, 0, 1, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1},
            {1, 2, 1, 1, 1, 1, 1},
            {0, 1, 2, 2, 2, 1, 1},
            {1, 1, 1, 2, 0, 2, 1},
            {0, 1, 0, 0, 1, 1, 0},
            {0, 1, 0, 0, 1, 1, 0},
            {0, 2, 1, 1, 2, 2, 0},
            {0, 0, 0, 0, 0, 0, 0}, //{1, 1, 1, 2, 1, 2, 2},
            {0, 1, 1, 0, 0, 0, 0},
            {2, 2, 2, 2, 2, 2, 2},
            {0, 2, 2, 2, 0, 0, 0},
            {0, 2, 1, 1, 2, 1, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0},
};


    static int[][] participantsDiscoveryChoices = {
            {1, 1, 0, 0, 1, 1},
            {2, 2, 0, 0, 0, 1},
            {2, 1, 1, 0, 0, 0},
            {0, 2, 0, 1, 2, 2},
            {0, 1, 1, 1, 1, 0},
            {0, 1, 0, 1, 1, 1},
            {0, 1, 1, 0, 1, 0},
            {1, 1, 0, 1, 1, 0},
            {1, 2, 2, 1, 0, 0},
            {2, 2, 0, 1, 1, 2},
            {1, 1, 1, 1, 1, 1},
            {2, 2, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 2, 0, 1, 0, 0},
            {0, 0, 2, 0, 0, 2},
            {0, 0, 2, 0, 0, 1},
            {0, 0, 0, 2, 2, 1},
            {1, 2, 0, 2, 0, 2},
            {0, 2, 1, 1, 1, 2},
            {2, 2, 1, 0, 0, 0},
            {1, 1, 0, 0, 1, 1},
            {2, 2, 1, 2, 0, 2},
            {0, 1, 0, 2, 2, 2},
            {0, 2, 0, 1, 0, 0},
            {2, 2, 1, 2, 1, 1},
            {0, 1, 0, 1, 2, 0},
            {0, 2, 0, 1, 0, 0},
            {0, 0, 1, 0, 0, 1},
            {0, 2, 0, 1, 1, 0},
            {1, 1, 1, 1, 1, 1},
            {1, 2, 1, 1, 1, 1},
            {1, 1, 2, 2, 2, 2},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {1, 1, 2, 2, 0, 1},
            {0, 1, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 2},
            {0, 0, 0, 0, 1, 1},
            {2, 2, 1, 2, 0, 1},
            {1, 1, 0, 1, 2, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 1, 2, 0, 0, 1},
            {1, 0, 1, 1, 0, 1},
            {2, 2, 0, 0, 0, 1},
            {2, 2, 2, 1, 0, 2},
            {0, 2, 1, 2, 0, 2},
            {1, 0, 1, 0, 0, 0},
            {1, 2, 0, 1, 0, 2},
            {1, 2, 0, 0, 0, 1},
            {1, 2, 1, 1, 1, 0},
            {0, 0, 0, 0, 1, 2},
            {0, 1, 0, 0, 2, 2},
            {0, 1, 0, 1, 0, 2},
            {0, 0, 0, 0, 0, 0},//{0, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 2, 0},
            {1, 1, 1, 1, 1, 1},
            {0, 2, 0, 2, 2, 0},
            {1, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0},
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
        duties[1] = Set.of(59, 48, 5, 6, 27);
        duties[2] = Set.of();
        duties[3] = Set.of();
        duties[4] = Set.of(55, 51, 60, 3, 50);
        duties[5] = Set.of();
        duties[6] = Set.of(54, 23, 26, 28, 13);

        /* Teaching duties */
        List<Integer>[] salsaProfs = new List[salsaClasses.length],
                bachataProfs = new List[bachataClasses.length],
                discoveryProfs = new List[discoveryClasses.length];
        // see participantNames for the name of the profs
        salsaProfs[0] = List.of(ILA);
        salsaProfs[1] = List.of(ILA, PA);
        salsaProfs[2] = List.of(19, 31);
        salsaProfs[3] = List.of(VALENTIN, LISON);
        salsaProfs[4] = List.of(ESTE, PA);
        salsaProfs[STYLING_HISTORY_ID] = List.of(MATTHIEU);
        salsaProfs[6] = List.of(ROBIN, 48);
        salsaProfs[7] = List.of(ROBIN, 13);
        bachataProfs[0] = List.of(LISON, PATRICK);
        bachataProfs[1] = List.of(PATRICK);
        bachataProfs[2] = List.of(BORIS, TEYA);
        bachataProfs[3] = List.of(BORIS, TEYA);
        bachataProfs[4] = List.of(YOS);
        bachataProfs[5] = List.of(YOS, JULIE);
        bachataProfs[6] = List.of(YOS, LISON);
        discoveryProfs[0] = List.of(ILA);
        discoveryProfs[1] = List.of(ILA);
        discoveryProfs[2] = List.of(LISON, LUCA);
        discoveryProfs[3] = List.of();
        discoveryProfs[4] = List.of(39, 54);
        discoveryProfs[5] = List.of(LUCA);

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
        /* Data cleaning : remove styling and estimate salsa history class using maximum likelihood estimation */
        for (int p = 0; p < participantsSalsaChoices.length; ++p) {
            int twos = 0, ones = 0, zeros = 0;
            for (int s = 0; s < salsaClasses.length; ++s) {
                if (participantsSalsaChoices[p][s] == I_DONT_WANNA_MISS_IT) {
                    twos++;
                } else if (participantsSalsaChoices[p][s] == INTERESTED) {
                    ones++;
                } else if (!salsaProfs[s].contains(p)) { // profs self votes don't count in estimation
                    zeros++;
                }
            }
            int max = Integer.max(Integer.max(zeros, ones), twos);
            int estimatedVote;
            if (max == zeros) {
                estimatedVote = NOT_INTERESTED;
            } else if (max == ones) {
                estimatedVote = INTERESTED;
            } else {
                estimatedVote = I_DONT_WANNA_MISS_IT;
            }
            participantsSalsaChoices[p][STYLING_HISTORY_ID] = estimatedVote;
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
        List<Integer>[] sameProfSalsaToSalsa = new List[salsaClasses.length];
        List<Integer>[] sameProfBachataToDiscovery = new List[bachataClasses.length];
        for (int s = 0; s < sameProfSalsaToSalsa.length; ++s) {
            sameProfSalsaToBachata[s] = new ArrayList<>();
            sameProfSalsaToDiscovery[s] = new ArrayList<>();
            sameProfSalsaToSalsa[s] = new ArrayList<>();
            for (int ss = 0; ss < salsaProfs.length; ++ss) {
                if (salsaProfs[s].stream().anyMatch(salsaProfs[ss]::contains)) {
                    sameProfSalsaToSalsa[s].add(ss);
                }
            }
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

        if (salsaClasses.length + bachataClasses.length + discoveryClasses.length != NUM_CLASSROOMS * TIME_SLOTS) {
            throw new RuntimeException("Not Enough classes for the time slots");
        }
        int numClasses = salsaClasses.length + bachataClasses.length + discoveryClasses.length;
        System.out.println("--- Number of classes " + numClasses);


        /* Solve */
        Optimizer optimizer = new Optimizer(salsaVoters, bachataVoters, discoveryVoters, salsaInterestVoters, bachataInterestVoters, discoveryInterestVoters,
                duties, salsaProfs, bachataProfs, discoveryProfs,
                sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery, sameProfSalsaToSalsa,
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
            classesSubset -= 1 << (bachataOffset + bestGuess.timeSlot[CLASSROOM_TWO_ID]);
            if (bestGuess.hasClassThreeSalsa) {
                classesSubset -= 1 << (salsaOffset + bestGuess.timeSlot[CLASSROOM_THREE_ID]);
            } else {
                classesSubset -= 1 << (bestGuess.timeSlot[CLASSROOM_THREE_ID]);
            }
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
            classesSubset -= 1 << (bachataOffset + bestGuess.timeSlot[CLASSROOM_TWO_ID]);
            if (bestGuess.hasClassThreeSalsa) {
                classesSubset -= 1 << (salsaOffset + bestGuess.timeSlot[CLASSROOM_THREE_ID]);
            } else {
                classesSubset -= 1 << (bestGuess.timeSlot[CLASSROOM_THREE_ID]);
            }
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
        Set<Integer> interestedInBachata = bachata[bestGuess.timeSlot[CLASSROOM_TWO_ID]];
        Set<Integer> interestedInThirdClass = bestGuess.hasClassThreeSalsa ? salsa[bestGuess.timeSlot[CLASSROOM_THREE_ID]] : discovery[bestGuess.timeSlot[CLASSROOM_THREE_ID]];
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
        onDuty.addAll(bachataProfs[bestGuess.timeSlot[CLASSROOM_TWO_ID]]);
        onDuty.addAll(bestGuess.hasClassThreeSalsa ? salsaProfs[bestGuess.timeSlot[CLASSROOM_THREE_ID]] : discoveryProfs[bestGuess.timeSlot[CLASSROOM_THREE_ID]]);

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
        return bachataClasses[bestGuess.timeSlot[CLASSROOM_TWO_ID]] + "(" + bachataVotes[bestGuess.timeSlot[CLASSROOM_TWO_ID]] + ", "
                + bachataInterestVotes[bestGuess.timeSlot[CLASSROOM_TWO_ID]]  + ", " + (bachataVotes[bestGuess.timeSlot[CLASSROOM_TWO_ID]]
                + INTEREST_WEIGHT * bachataInterestVotes[bestGuess.timeSlot[CLASSROOM_TWO_ID]]) + ")" + "\t\t";
    }

    static String printC(BestGuess bestGuess) {
        if (bestGuess.hasClassThreeSalsa) {
            return salsaClasses[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + "(" + salsaVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + ", "
                    + salsaInterestVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]] +  ", " + (salsaVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]]
                    + INTEREST_WEIGHT * salsaInterestVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]])  +  ")" + "\t\t";
        }
        return discoveryClasses[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + "(" + discoveryVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]] + ", "
                + discoveryInterestVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]] +  ", " + (discoveryVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]]
                + INTEREST_WEIGHT * discoveryInterestVotes[bestGuess.timeSlot[CLASSROOM_THREE_ID]]) + ")" + "\t\t";

    }

    private static class Optimizer {
        public static final int YOS_1 = 5;
        public static final int HIPHOP = 0;
        private static final int AFRORUMBA = 0;
        Map<Integer, BestGuess> memo = new HashMap<>();
        Set<Integer>[] salsaVoters, bachataVoters, discoveryVoters, salsaInterestVoters, bachataInterestVoters, discoveryInterestVoters, duties;
        List<Integer>[] sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery, sameProfSalsaToSalsa;
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
                  List<Integer>[] sameProfSalsaToSalsa,
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
            this.sameProfSalsaToSalsa = sameProfSalsaToSalsa;
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
                float m = -1.0f/(MAX_COUNT - TIME_SLOTS);
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

        float solve(int timeSlotId, int classesSubset, boolean extraSalsa) {
            if (timeSlotId >= TIME_SLOTS) { // Base case
                boolean allClassesOnSchedule = classesSubset == 0;
                return allClassesOnSchedule ? 0.0f : INFINITY;
            }
            BestGuess ans = memo.get(classesSubset);
            if (ans != null) {
                return ans.score;
            }
            ans = guessSalsa(timeSlotId, classesSubset, extraSalsa, new int[NUM_CLASSROOMS]);
            memo.put(classesSubset, ans);
            return ans.score;
        }

        BestGuess guessSalsa(int timeSlotId, int classesSubset, boolean extraSalsa, int[] timeSlot) {
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
                    BestGuess someGuess = guessBachata(timeSlotId, classesSubset - classMask, extraSalsa, timeSlot);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_ONE_ID] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess guessBachata(int timeSlotId, int classesSubset, boolean extraSalsa, int[] timeSlot) {
            BestGuess bestGuess = IMPOSSIBLE;
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
                    BestGuess someGuess = guessDiscovery(timeSlotId, classesSubset - classMask, extraSalsa, timeSlot);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_TWO_ID] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess guessDiscovery(int timeSlotId, int classesSubset, boolean extraSalsa, int[] timeSlot) {
            BestGuess bestGuess = IMPOSSIBLE;
            if (extraSalsa) {
                int offset = bachataVoters.length + discoveryVoters.length;
                for (int currClass = 0; currClass < salsaVoters.length; ++currClass) {
                    if (isProfAlreadyInTimeSlot(currClass, sameProfSalsaToSalsa, timeSlot[CLASSROOM_ONE_ID])
                            || isProfAlreadyInTimeSlot(timeSlot[CLASSROOM_TWO_ID], sameProfSalsaToBachata, currClass)
                            || isProfUnavailable(currClass, timeSlotId, salsaProfsUnavailable)) {
                        continue;
                    }
                    int classMask = 1 << (offset + currClass);
                    boolean isClassNotPicked = (classesSubset & classMask) > 0;
                    if (isClassNotPicked) {
                        timeSlot[CLASSROOM_THREE_ID] = currClass;
                        BestGuess someGuess = endOfTimeSlot(timeSlotId, classesSubset - classMask, false, true, timeSlot);
                        if (someGuess.score < bestGuess.score) {
                            someGuess.timeSlot[CLASSROOM_THREE_ID] = currClass;
                            someGuess.hasClassThreeSalsa = true;
                            bestGuess = someGuess;
                        }
                    }
                }
            }
            int offset = 0;
            for (int currClass = 0; currClass < discoveryVoters.length; ++currClass) {
                if (isProfAlreadyInTimeSlot(currClass, sameProfSalsaToDiscovery, timeSlot[CLASSROOM_ONE_ID])
                        || isProfAlreadyInTimeSlot(currClass, sameProfBachataToDiscovery, timeSlot[CLASSROOM_TWO_ID])
                        || isProfUnavailable(currClass, timeSlotId, bachataProfsUnavailable)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked = (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_THREE_ID] = currClass;
                    BestGuess someGuess = endOfTimeSlot(timeSlotId, classesSubset - classMask, extraSalsa, false, timeSlot);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_THREE_ID] = currClass;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess endOfTimeSlot(int timeSlotId, int classesSubset, boolean extraSalsa, boolean hasClassThreeSalsa, int[] timeSlot) {
            float lookup = solve(timeSlotId + 1, classesSubset, extraSalsa);
            float loss = computeLoss(timeSlotId, classesSubset, timeSlot, hasClassThreeSalsa);
            return lookup > SCORE_LIMIT ? IMPOSSIBLE : new BestGuess(loss + lookup);
        }

        boolean isProfAlreadyInTimeSlot(int b, List<Integer>[] sameProfAtoB, int a) {
            return sameProfAtoB[a].stream().anyMatch(otherClassTheyGive -> b == otherClassTheyGive);
        }

        boolean isProfUnavailable(int currClass, int timeSlotId, Set<Integer>[] profsOnDuty) {
            return profsOnDuty[currClass].contains(timeSlotId);
        }

        private float computeAverage(int[] timeSlot, int[] aVotes, int[] bVotes, int[] cVotes) {
            float count = 3.0f;
            return (aVotes[timeSlot[CLASSROOM_ONE_ID]] + bVotes[timeSlot[CLASSROOM_TWO_ID]] + cVotes[timeSlot[CLASSROOM_THREE_ID]]) / count;
        }

        float weightedSum(Set<Integer> A, float[] w) {
            return A.stream().map(p -> w[p]).reduce(0.0f, Float::sum);
        }

        float[][][][] salsaPenalty, penalty;

        // implements loss = |A| + |B| + |C| - |AuBuC| + |D(AuBuC)|
        // Note: styling has been removed from the program so it's a placeholder for empty
        void initLossLookup(Set<Integer>[] thirdClass, Set<Integer>[] thirdClassInterest, List<Integer>[] thirdClassProfs, float[][][][] result) {
            for (int s = 0; s < salsaVoters.length; ++s) {
                for (int b = 0; b < bachataVoters.length; ++b) {
                    for (int t = 0; t < thirdClass.length; ++t) {
                        Set<Integer> AuBuC = new HashSet<>();
                        AuBuC.addAll(salsaVoters[s]);
                        AuBuC.addAll(bachataVoters[b]);
                        AuBuC.addAll(thirdClass[t]);
                        Set<Integer> AuBuCInterest = new HashSet<>();
                        AuBuCInterest.addAll(salsaInterestVoters[s]);
                        AuBuCInterest.addAll(bachataInterestVoters[b]);
                        AuBuCInterest.addAll(thirdClassInterest[t]);
                        for (int i = 0; i < TIME_SLOTS; ++i) {
                            Set<Integer> DAuBuC = new HashSet<>(duties[i]);
                            /* Count profs as on duty, because they have no option just like the people in the kitchen */
                            DAuBuC.addAll(salsaProfs[s]);
                            DAuBuC.addAll(bachataProfs[b]);
                            DAuBuC.addAll(thirdClassProfs[t]);
                            Set<Integer> DAuBuCInterest = new HashSet<>(DAuBuC); // like duties[i] but with profs
                            DAuBuC.retainAll(AuBuC);
                            DAuBuCInterest.retainAll(AuBuCInterest);
                            result[i][s][b][t] = weightedSum(salsaVoters[s], weights)
                                    + weightedSum(bachataVoters[b], weights)
                                    + weightedSum(thirdClass[t], weights)
                                    - weightedSum(AuBuC, weights) + weightedSum(DAuBuC, weights);
                            result[i][s][b][t] += weightedSum(salsaInterestVoters[s], interestWeights)
                                    + weightedSum(bachataInterestVoters[b], interestWeights)
                                    + weightedSum(thirdClassInterest[t], interestWeights)
                                    - weightedSum(AuBuCInterest, interestWeights) + weightedSum(DAuBuCInterest, interestWeights);
                        }
                    }
                }
            }
        }

        void initLossLookup() {
            System.out.println("--------- Init penalties ------------");
            penalty = new float[TIME_SLOTS][salsaVoters.length][bachataVoters.length][discoveryVoters.length];
            salsaPenalty = new float[TIME_SLOTS][salsaVoters.length][bachataVoters.length][salsaVoters.length];
            initLossLookup(discoveryVoters, discoveryInterestVoters, discoveryProfs, penalty);
            System.out.println("--------- Init salsa penalties ------------");
            initLossLookup(salsaVoters, salsaInterestVoters, salsaProfs, salsaPenalty);
            System.out.println("--------- Init penalties done ------------");

        }

        float computeLoss(int timeSlotId, int classesSubset, int[] timeSlot, boolean isClassThreeSalsa) {
            float loss = isClassThreeSalsa ? salsaPenalty[timeSlotId][timeSlot[CLASSROOM_ONE_ID]][timeSlot[CLASSROOM_TWO_ID]][timeSlot[CLASSROOM_THREE_ID]]
                    : penalty[timeSlotId][timeSlot[CLASSROOM_ONE_ID]][timeSlot[CLASSROOM_TWO_ID]][timeSlot[CLASSROOM_THREE_ID]];
            // Virginia has to leave sooner
            if (!isClassThreeSalsa && timeSlot[CLASSROOM_THREE_ID] == SAMBA && timeSlotId != FIRST_CLASS) {
                loss += 10000000;
            }

            if (!isClassThreeSalsa && timeSlot[CLASSROOM_ONE_ID] == SKANDER_MUSIC && timeSlot[CLASSROOM_THREE_ID] == HIPHOP) {
                loss += 10000000;
            }

            // put history with PW
            boolean isHistory = timeSlot[CLASSROOM_ONE_ID] == STYLING_HISTORY_ID || (isClassThreeSalsa && timeSlot[CLASSROOM_THREE_ID] == STYLING_HISTORY_ID);
            boolean isPartnerwork = timeSlot[CLASSROOM_ONE_ID] == VALENTIN_PW || (isClassThreeSalsa && timeSlot[CLASSROOM_THREE_ID] == VALENTIN_PW)
                    || timeSlot[CLASSROOM_ONE_ID] == MARLA_PW || (isClassThreeSalsa && timeSlot[CLASSROOM_THREE_ID] == MARLA_PW)
                    || timeSlot[CLASSROOM_ONE_ID] == KAI_PW || (isClassThreeSalsa && timeSlot[CLASSROOM_THREE_ID] == KAI_PW);
            if (isHistory && !isPartnerwork) {
                loss += 10000000;
            }
            // put bla bla when people can rest
            if (isHistory && timeSlotId < AFTER_SATURDAY_LUNCH) {
                loss += 10000000;
            }

            // at least one intermediate
            int s = timeSlot[CLASSROOM_ONE_ID];
            boolean isSalsaInter = interSalsa.contains(s);
            if (!isSalsaInter && isClassThreeSalsa) {
                s = timeSlot[CLASSROOM_THREE_ID];
                isSalsaInter = interSalsa.contains(s);
            }
            boolean isBachataInter = interBachata.contains(timeSlot[CLASSROOM_TWO_ID]);
            if (!isSalsaInter && !isBachataInter) {
                loss += 10000000;
            }

            if (!isClassThreeSalsa && timeSlot[CLASSROOM_ONE_ID] == SKANDER_MUSIC && timeSlot[CLASSROOM_THREE_ID] == REGGAE) {
                loss += 10000000;
            }

//            if (!isClassThreeSalsa && timeSlot[CLASSROOM_THREE_ID] == CALENA && timeSlotId == SATURDAY_LUNCH) {
//                loss += 10000000;
//            }


//            if (!isClassThreeSalsa && timeSlot[CLASSROOM_ONE_ID] == URBAN_SALSA && timeSlot[CLASSROOM_THREE_ID] == SAMBA) {
//                loss += 10000000;
//            }
//            if (!isClassThreeSalsa && timeSlot[CLASSROOM_ONE_ID] == SKANDER_MUSIC && timeSlot[CLASSROOM_THREE_ID] == SAMBA) {
//                loss += 10000000;
//            }
//

//            if (isClassThreeSalsa && (timeSlot[CLASSROOM_ONE_ID] == AFRORUMBA && timeSlot[CLASSROOM_THREE_ID] == SKANDER_MUSIC)
//                    || timeSlot[CLASSROOM_THREE_ID] == AFRORUMBA && timeSlot[CLASSROOM_ONE_ID] == SKANDER_MUSIC) {
//                loss += 10000000;
//            }

            // Note: styling has been removed from the program so it's a placeholder for empty
//            if (!isClassThreeSalsa && (timeSlot[CLASSROOM_ONE_ID] == STYLING && timeSlot[CLASSROOM_THREE_ID] == SAMBA)) {
//                loss += 10000000;
//            }
//            if (!isClassThreeSalsa && timeSlot[CLASSROOM_ONE_ID] == STYLING && timeSlot[CLASSROOM_THREE_ID] == REGGAE) {
//                loss += 10000000;
//            }
//            if (isClassThreeSalsa && (timeSlot[CLASSROOM_ONE_ID] == AFRORUMBA && timeSlot[CLASSROOM_THREE_ID] == STYLING)
//                    || timeSlot[CLASSROOM_THREE_ID] == AFRORUMBA && timeSlot[CLASSROOM_ONE_ID] == STYLING) {
//                loss += 10000000;
//            }
//            boolean isStyling = timeSlot[CLASSROOM_ONE_ID] == STYLING || (isClassThreeSalsa && timeSlot[CLASSROOM_THREE_ID] == STYLING);
//            if (isStyling && timeSlotId != STYLING_TIME) {
//                loss += 10000000;
//            }


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
            int[] cVotes = bestGuess.hasClassThreeSalsa ? salsaVotes : discoveryVotes;
            return computeAverage(bestGuess.timeSlot, salsaVotes, bachataVotes, cVotes) / heuristicTarget * 100.0f;
        }
    }

    enum ClassLevel {
        INTERMEDIATE,
        ADVANCED
    }

    private static class BestGuess {
        float score;
        int[] timeSlot;
        boolean hasClassThreeSalsa;

        BestGuess(float score) {
            this.score = score;
            timeSlot = new int[NUM_CLASSROOMS];
            for (int i = 0; i < NUM_CLASSROOMS; ++i) {
                timeSlot[i] = -1;
            }
        }
    }
}
