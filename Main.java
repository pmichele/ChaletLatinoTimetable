import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;

/**
 *
 * @author Michele Pettinato
 */
public class Main {

    static final int TIME_SLOTS = 7, CLASSROOM_A_ID = 0, CLASSROOM_B_ID = 1, NUM_CLASSROOMS = 2, END_OF_TIME_SLOT = NUM_CLASSROOMS, NO_CLASS = -1;
    static final float INFINITY = Integer.MAX_VALUE, SCORE_LIMIT = 1_000_000_000.0f;

    static String[] salsaNames = new String[] {
            "Salsa fusion by P.A. y Este",
            "Arm work salsa Fusion by Ilaria y Harshit",
            "Salsa all style by Caroline y Larissa",
            "Pasitos salsa con afro by Angelica Fino",
            "Lady Styling by Angelica Fino (man vs girl) / Man Styling by P.A. (man vs girl)"
    };
    static String[] bachataNames = new String[] {
            "Bachata moderne by Patrick y Sofia",
            "Breaks and musicality partner work by Patrick",
            "Smooth transitions by Thanu y Sofia",
            "Sensual couple connection by Thanu y Larissa"
    };
    static String[] discoveryNames = new String[] {
            "Reggaetton fusion by Ilaria",
            "Hip Hop by Ilaria",
            "Zouk Flow Introduction by Thanu y Julie",
            "Rumba by Angelica Fino"
    };

    static String[] participants = new String[] {
            "",
            "",
            "Ilaria Ricchi",
            "Angelica Fino",
            "Tessa Rougemont",
            "Matteo Mancuso",
            "Caroline Schmitt",
            "Léa Consuegra",
            "Nathalie Aney Guzmán Santa Cruz",
            "Richard Santiago",
            "Maja Stamenkovic",
            "Célina Marques",
            "Mariona Lopez GIl",
            "Ruth Díaz",
            "Thierry ",
            "Felix Hans Michel Grimberg ",
            "Balsiger Gloria ",
            "Sofia Echevarria",
            "Laurent Bugnard",
            "Igor Krawczuk ",
            "Léonore Baumann",
            "Matthieu Stigler",
            "Skander Moalla",
            "Miriam Pougin ",
            "Sonja Hirt",
            "Larissa Schuh",
            "Svitlana Mykolenko",
            "Emma Favrod",
            "Harshit Khurana",
            "Milena Schuhmacher ",
            "Florent GIFFON ",
            "Valentin Gobert",
            "Miguel Basante Bedoya ",
            "Thomas Stutz ",
            "Thanushan Kugathasan",
            "Patrick Sgrò ",
            "Michele Pettinato",
            "P.A.",
            "Estefania Arroyo",
            "Julie Hernandez ",
            "Sebastian Santos",
            "Alice Moraz",
            "Heidi Lee",
            "Hayoz Charly",
            "Tristan Reinhard"
    };

    // Note: I removed profs who voted for themselves to boost the value for the classes they actually want to do
    static String[] preferences = new String[] {
            "",
            "",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Rumba by Angelica Fino",
            "none",
            "Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Arm work salsa Fusion by Ilaria y Harshit,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino,Bachata moderne by Patrick y Sofia,Rumba by Angelica Fino",
            "Pasitos salsa con afro by Angelica Fino, Salsa fusion by P.A. y Este,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Smooth transitions by Thanu y Sofia,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Smooth transitions by Thanu y Sofia,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa",
            "Lady Styling by Angelica Fino (man vs girl), Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa",
            "Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit,Sensual couple connection by Thanu y Larissa",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Arm work salsa Fusion by Ilaria y Harshit,Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este,Bachata moderne by Patrick y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit,Breaks and musicality partner work by Patrick,Rumba by Angelica Fino",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Salsa all style by Caroline y Larissa,Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Lady Styling by Angelica Fino (man vs girl), Salsa all style by Caroline y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este,Sensual couple connection by Thanu y Larissa,Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl),Rumba by Angelica Fino",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Arm work salsa Fusion by Ilaria y Harshit,Breaks and musicality partner work by Patrick,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Breaks and musicality partner work by Patrick, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl),Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino,Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Arm work salsa Fusion by Ilaria y Harshit,Bachata moderne by Patrick y Sofia, Smooth transitions by Thanu y Sofia,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit,Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Breaks and musicality partner work by Patrick, Rumba by Angelica Fino, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl),Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit,Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Rumba by Angelica Fino, Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl),Rumba by Angelica Fino",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este,Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria, Reggaetton fusion by Ilaria",
            "none",
            "Pasitos salsa con afro by Angelica Fino, Lady Styling by Angelica Fino (man vs girl), Salsa fusion by P.A. y Este, Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa,Hip Hop by Ilaria, Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Lady Styling by Angelica Fino (man vs girl), Arm work salsa Fusion by Ilaria y Harshit, Salsa all style by Caroline y Larissa,Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Sensual couple connection by Thanu y Larissa,Reggaetton fusion by Ilaria, Zouk Flow Introduction by Thanu y Julie",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl),Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa",
            "Pasitos salsa con afro by Angelica Fino, Man Styling by P.A. (man vs girl), Salsa fusion by P.A. y Este, Salsa all style by Caroline y Larissa, Bachata moderne by Patrick y Sofia, Breaks and musicality partner work by Patrick, Smooth transitions by Thanu y Sofia, Sensual couple connection by Thanu y Larissa"
    };

    static int[] salsaVotes;
    static int[] bachataVotes;
    static int[] discoveryVotes;

    static int salsaStylingId = 4;

    public static void main(String[] args) {

        salsaVotes = new int[salsaNames.length];
        bachataVotes = new int[bachataNames.length];
        discoveryVotes = new int[discoveryNames.length];

        /* Inverse preferences map and votes */
        Set<Integer>[] salsa = new Set[salsaVotes.length];
        Set<Integer>[] bachata = new Set[bachataVotes.length];
        Set<Integer>[] discovery = new Set[discoveryVotes.length];
        int[] numPreferences = new int[preferences.length];
        for (int i = 0; i < salsa.length; ++i) {
            salsa[i] = new HashSet<>();
        }
        for (int i = 0; i < bachata.length; ++i) {
            bachata[i] = new HashSet<>();
        }
        for (int i = 0; i < discovery.length; ++i) {
            discovery[i] = new HashSet<>();
        }

        for (int p = 0; p < preferences.length; ++p) {
            for (String preference : preferences[p].split(",")) {
                preference = preference.trim();
                if (preference.isEmpty()) {
                    continue;
                }
                ++numPreferences[p];
                boolean found = false;
                for (int i = 0; !found && i < salsaNames.length; ++i) {
                    if (salsaNames[i].contains(preference)) {
                        ++salsaVotes[i];
                        salsa[i].add(p);
                        found = true;
                    }
                }
                for (int i = 0; !found && i < bachataNames.length; ++i) {
                    if (bachataNames[i].contains(preference)) {
                        ++bachataVotes[i];
                        bachata[i].add(p);
                        found = true;
                    }
                }
                for (int i = 0; !found && i < discoveryNames.length; ++i) {
                    if (discoveryNames[i].contains(preference)) {
                        ++discoveryVotes[i];
                        discovery[i].add(p);
                        found = true;
                    }
                }
            }
        }

        /* People on duties (kitchen) */
        Set<Integer>[] duties = new Set[TIME_SLOTS];
        duties[0] = Set.of();
        duties[1] = Set.of(28, 42, 32, 44);
        duties[2] = Set.of();
        duties[3] = Set.of();
        duties[4] = Set.of(43, 7, 21, 18);
        duties[5] = Set.of();
        duties[6] = Set.of(4, 5, 8, 15);

        /* Teaching duties */
        List<Integer>[] salsaProfs = new List[salsaNames.length],
                bachataProfs = new List[bachataNames.length],
                discoveryProfs = new List[discoveryNames.length];
        salsaProfs[0] = List.of(37, 38);
        salsaProfs[1] = List.of(2, 28);
        salsaProfs[2] = List.of(6, 25);
        salsaProfs[3] = List.of(3);
        salsaProfs[4] = List.of(3, 37);
        bachataProfs[0] = List.of(35, 17);
        bachataProfs[1] = List.of(35);
        bachataProfs[2] = List.of(34, 17);
        bachataProfs[3] = List.of(34, 25);
        discoveryProfs[0] = List.of(2);
        discoveryProfs[1] = List.of(2);
        discoveryProfs[2] = List.of(34, 39);
        discoveryProfs[3] = List.of(3);


        // 1. no same prof in parallel constraints
        List<Integer>[] sameProfSalsaToBachata = new List[salsaVotes.length];
        List<Integer>[] sameProfSalsaToDiscovery = new List[salsaVotes.length];
        List<Integer>[] sameProfBachataToDiscovery = new List[bachataVotes.length];
        for (int i = 0; i < sameProfSalsaToBachata.length; ++i) {
            sameProfSalsaToBachata[i] = new ArrayList<>();
            sameProfSalsaToDiscovery[i] = new ArrayList<>();
        }
        for (int i = 0; i < sameProfBachataToDiscovery.length; ++i) {
            sameProfBachataToDiscovery[i] = new ArrayList<>();
        }
        sameProfSalsaToBachata[2] = Arrays.asList(3); // Larissa teaches 1 bachata
        sameProfSalsaToDiscovery[1] = Arrays.asList(0, 1); // Ilaria does 2 initiations
        sameProfSalsaToDiscovery[3] = Arrays.asList(3); // angelica does rumba
        sameProfSalsaToDiscovery[4] = Arrays.asList(3); // angelica does rumba


        // 2. Profs on duties constraints
        List<Integer>[] salsaProfsOnDuty = new List[salsaVotes.length];
        List<Integer>[] bachataProfsOnDuty = new List[bachataVotes.length];
        List<Integer>[] discoveryProfsOnDuty = new List[discoveryVotes.length];
        for (int i = 0; i < salsaProfsOnDuty.length; ++i) {
            salsaProfsOnDuty[i] = new ArrayList<>();
        }
        for (int i = 0; i < bachataProfsOnDuty.length; ++i) {
            bachataProfsOnDuty[i] = new ArrayList<>();
        }
        for (int i = 0; i < discoveryProfsOnDuty.length; ++i) {
            discoveryProfsOnDuty[i] = new ArrayList<>();
        }
        salsaProfsOnDuty[1] = Arrays.asList(1); // harshit cooks on workshop 2
        bachataProfsOnDuty[0] = Arrays.asList(1, 4, 6); // Patrick must be available at the kitchen
        bachataProfsOnDuty[1] = Arrays.asList(1, 4, 6); // Patrick must be available at the kitchen

        if ((salsaVotes.length + 1) + bachataVotes.length + discoveryVotes.length != NUM_CLASSROOMS * TIME_SLOTS) {
            throw new RuntimeException("Not Enough classes for the time slots");
        }
        int numClasses = salsaVotes.length + bachataVotes.length + discoveryVotes.length;
        System.out.println("--- Number of classes " + numClasses);


        /* Solve */
        Optimizer optimizer = new Optimizer(salsa, bachata, discovery, duties, numPreferences, salsaProfs, bachataProfs, discoveryProfs,
                sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery,
                salsaProfsOnDuty, bachataProfsOnDuty, discoveryProfsOnDuty, salsaStylingId);
        float bestScore = optimizer.solve(0, (1 << numClasses) - 1, 2, 2, 2);
        System.out.println("--- Average Loss " + (bestScore / TIME_SLOTS));
        System.out.println();

        if (bestScore > SCORE_LIMIT) {
            System.out.println("Impossible !!");
            return;
        }

        /* Visualize */
        for (int i = 0, classesSubset = (1 << numClasses) - 1; i < TIME_SLOTS; ++i) {
            BestGuess bestGuess = optimizer.memo.get(classesSubset);
            System.out.print(printA(bestGuess));
            System.out.print(printB(bestGuess));
            int[] aVotes = bestGuess.isSalsaInA ? salsaVotes : bachataVotes;
            int[] bVotes = bestGuess.isBachataInB ? bachataVotes : discoveryVotes;
            System.out.println(optimizer.computeSimilarity(bestGuess.timeSlot, aVotes, bVotes) + "%");
            int aOffset = bestGuess.isSalsaInA ? (bachataVotes.length + discoveryVotes.length) : discoveryVotes.length;
            int bOffset = bestGuess.isBachataInB ? discoveryVotes.length : 0;
            classesSubset -= 1 << (aOffset + bestGuess.timeSlot[CLASSROOM_A_ID]);
            if (!bestGuess.isSalsaInA || bestGuess.timeSlot[CLASSROOM_A_ID] != salsaStylingId) {
                classesSubset -= 1 << (bOffset + bestGuess.timeSlot[CLASSROOM_B_ID]);
            }
        }

        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();
        int[] missingPreferences = new int[participants.length];
        for (int i = 0, classesSubset = (1 << numClasses) - 1; i < TIME_SLOTS; ++i) {
            BestGuess bestGuess = optimizer.memo.get(classesSubset);
            showResultPerPerson(bestGuess, salsa, bachata, discovery, salsaProfs, bachataProfs, discoveryProfs, duties, i, missingPreferences);
            int aOffset = bestGuess.isSalsaInA ? (bachataVotes.length + discoveryVotes.length) : discoveryVotes.length;
            int bOffset = bestGuess.isBachataInB ? discoveryVotes.length : 0;
            classesSubset -= 1 << (aOffset + bestGuess.timeSlot[CLASSROOM_A_ID]);
            if (!bestGuess.isSalsaInA || bestGuess.timeSlot[CLASSROOM_A_ID] != salsaStylingId) {
                classesSubset -= 1 << (bOffset + bestGuess.timeSlot[CLASSROOM_B_ID]);
            }
        }
        System.out.println();
        System.out.println("============================================================================================");
        System.out.println();

        System.out.println("People that will have to do less than what they chose");
        Person[] result = new Person[participants.length];
        for (int p = 0; p < participants.length; ++p) {
            result[p] = new Person();
            result[p].name = participants[p];
            result[p].numPreferences = preferences[p].split(",").length;
            result[p].missingPreferences = missingPreferences[p];
            result[p].score = missingPreferences[p] / (float) result[p].numPreferences;
        }
        Arrays.sort(result);
        for (Person person : result) {
            if (person.numPreferences - person.missingPreferences >= TIME_SLOTS || person.missingPreferences == 0) {
                continue;
            }
            System.out.println(person.name + "(" + person.numPreferences + "): " + (person.numPreferences - person.missingPreferences));
        }
    }

    static class Person implements Comparable<Person> {
        int numPreferences, missingPreferences;
        float score;
        String name;

        @Override
        public int compareTo(Person o) {
            return Float.compare(o.score, this.score);
        }
    }

    static void showResultPerPerson(BestGuess bestGuess, Set<Integer>[] salsa, Set<Integer>[] bachata, Set<Integer>[] discovery,
                                    List<Integer>[] salsaProfs, List<Integer>[] bachataProfs, List<Integer>[] discoveryProfs,
                                    Set<Integer>[] duties, int i, int[] missingPreferences) {
        Set<Integer>[] As = bestGuess.isSalsaInA ? salsa : bachata, Bs = bestGuess.isBachataInB ? bachata : discovery;
        Set<Integer> interestedInA = As[bestGuess.timeSlot[CLASSROOM_A_ID]];
        Set<Integer> interestedInB = bestGuess.timeSlot[CLASSROOM_B_ID] == NO_CLASS ? null : Bs[bestGuess.timeSlot[CLASSROOM_B_ID]];
        Set<Integer> interestedInBoth = new HashSet<>(), interestedInOneOrMore = new HashSet<>(interestedInA), onDutiesAndInterestedInOneOrMore = new HashSet<>(duties[i]);
        if (interestedInB != null) {
            interestedInBoth = new HashSet<>(interestedInA);
            interestedInBoth.retainAll(interestedInB);
            interestedInOneOrMore.addAll(interestedInB);
        }
        /* Count profs as on duty, because they have no option just like the people in the kitchen */
        List<Integer> profsA = bestGuess.isSalsaInA ? salsaProfs[bestGuess.timeSlot[CLASSROOM_A_ID]] : bachataProfs[bestGuess.timeSlot[CLASSROOM_A_ID]];
        List<Integer> profsB = new ArrayList<>();
        if (bestGuess.timeSlot[CLASSROOM_B_ID] != NO_CLASS) {
            profsB = !bestGuess.isBachataInB ? discoveryProfs[bestGuess.timeSlot[CLASSROOM_B_ID]] : bachataProfs[bestGuess.timeSlot[CLASSROOM_B_ID]];
        }
        onDutiesAndInterestedInOneOrMore.addAll(profsA);
        onDutiesAndInterestedInOneOrMore.addAll(profsB);

        onDutiesAndInterestedInOneOrMore.retainAll(interestedInOneOrMore);
        Set<Integer> onDutiesAndInterestedInBoth = new HashSet<>(interestedInBoth);
        onDutiesAndInterestedInBoth.retainAll(duties[i]);
        interestedInBoth.removeAll(onDutiesAndInterestedInBoth);
        onDutiesAndInterestedInOneOrMore.removeAll(onDutiesAndInterestedInBoth);
        System.out.println("WORKSHOP " + (i + 1) + ": List of people that wants to do both classes but miss one (" + interestedInBoth.size() + ")");
        for (int p : interestedInBoth) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p]++;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty that miss one class they want to do (" + onDutiesAndInterestedInOneOrMore.size() + ")");
        for (int p : onDutiesAndInterestedInOneOrMore) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p]++;
        }
        System.out.println("\nWORKSHOP " + (i + 1) + ": List of people on duty missing BOTH classes they want to do (" + onDutiesAndInterestedInBoth.size() + ")");
        for (int p : onDutiesAndInterestedInBoth) {
            System.out.print(participants[p].trim() + ", ");
            missingPreferences[p] += 2;
        }
        System.out.println();
        System.out.println();
        System.out.println();
    }

    static String printA(BestGuess bestGuess) {
        if (bestGuess.isSalsaInA) {
            return salsaNames[bestGuess.timeSlot[CLASSROOM_A_ID]] + "(" + salsaVotes[bestGuess.timeSlot[CLASSROOM_A_ID]] +  ")" + "\t\t";
        }
        return bachataNames[bestGuess.timeSlot[CLASSROOM_A_ID]] + "(" + bachataVotes[bestGuess.timeSlot[CLASSROOM_A_ID]] +  ")" + "\t\t";
    }

    static String printB(BestGuess bestGuess) {
        if (bestGuess.isSalsaInA && bestGuess.timeSlot[CLASSROOM_A_ID] == salsaStylingId) {
            return "";
        }
        if (bestGuess.isBachataInB) {
            return bachataNames[bestGuess.timeSlot[CLASSROOM_B_ID]] + "(" + bachataVotes[bestGuess.timeSlot[CLASSROOM_B_ID]] +  ")" + "\t\t";
        }
        return discoveryNames[bestGuess.timeSlot[CLASSROOM_B_ID]] + "(" + discoveryVotes[bestGuess.timeSlot[CLASSROOM_B_ID]] +  ")" + "\t\t";
    }

    private static class Optimizer {
        Map<Integer, BestGuess> memo = new HashMap<>();
        Set<Integer>[] salsa, bachata, discovery, duties;
        List<Integer>[] sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery;
        List<Integer>[] salsaProfsOnDuty, bachataProfsOnDuty, discoveryProfsOnDuty;

        List<Integer>[] salsaProfs, bachataProfs, discoveryProfs;
        final BestGuess IMPOSSIBLE = new BestGuess(INFINITY);
        float[] pWeight;

        final int salsaStylingId;
        final float target;

        Optimizer(Set<Integer>[] salsa, Set<Integer>[] bachata, Set<Integer>[] discovery, Set<Integer>[] duties, int[] numPreferences,
                  List<Integer>[] salsaProfs,
                  List<Integer>[] bachataProfs,
                  List<Integer>[] discoveryProfs,
                  List<Integer>[] sameProfSalsaToBachata,
                  List<Integer>[] sameProfSalsaToDiscovery, List<Integer>[] sameProfBachataToDiscovery,
                  List<Integer>[] salsaProfsOnDuty, List<Integer>[] bachataProfsOnDuty, List<Integer>[] discoveryProfsOnDuty,
                  int salsaStylingId) {
            this.salsa = salsa;
            this.bachata = bachata;
            this.discovery = discovery;
            this.duties = duties;
            this.salsaProfs = salsaProfs;
            this.bachataProfs = bachataProfs;
            this.discoveryProfs = discoveryProfs;
            this.sameProfSalsaToBachata = sameProfSalsaToBachata;
            this.sameProfSalsaToDiscovery = sameProfSalsaToDiscovery;
            this.sameProfBachataToDiscovery = sameProfBachataToDiscovery;
            this.salsaProfsOnDuty = salsaProfsOnDuty;
            this.bachataProfsOnDuty = bachataProfsOnDuty;
            this.discoveryProfsOnDuty = discoveryProfsOnDuty;
            this.salsaStylingId = salsaStylingId;
            pWeight = new float[numPreferences.length];
            for (int p = 0; p < numPreferences.length; ++p) {
                pWeight[p] = numPreferences[p] < 8 ? (8 - numPreferences[p]) : (-1.0f/6.0f * numPreferences[p] + 13.0f/6.0f);
            }
            this.target = (float) (Arrays.stream(salsaVotes).sum() + Arrays.stream(bachataVotes).sum() + Arrays.stream(discoveryVotes).sum())
                        / (salsaVotes.length + bachataVotes.length + discoveryVotes.length);
            System.out.println("--- Average " + target);
        }

        float solve(int timeSlotId, int classesSubset, int SB, int SD, int BD) {
            if (timeSlotId >= TIME_SLOTS) { // Base case
                boolean allClassesOnSchedule = classesSubset == 0;
                return allClassesOnSchedule ? 0.0f : INFINITY;
            }
            BestGuess ans = memo.get(classesSubset);
            if (ans != null) {
                return ans.score;
            }
            ans = guessA(timeSlotId, classesSubset, SB, SD, BD, new int[NUM_CLASSROOMS]);
            memo.put(classesSubset, ans);
            return ans.score;
        }

        BestGuess guessA(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot) {
            // guess Salsa in classroom A
            BestGuess bestGuess = guessA(timeSlotId, classesSubset, SB, SD, BD, timeSlot, bachata.length + discovery.length,
                    salsa.length, salsaProfsOnDuty, true, IMPOSSIBLE);
            // guess Bachata in classroom A
            return guessA(timeSlotId, classesSubset, SB, SD, BD, timeSlot, discovery.length,
                    bachata.length, bachataProfsOnDuty, false, bestGuess);
        }
        BestGuess guessA(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot,
                         int offset, int numClasses, List<Integer>[] profsOnDuty, boolean isSalsaInA,
                         BestGuess bestGuess) {
            for (int currClass = 0; currClass < numClasses; ++currClass) {
                if (isProfOnDuty(currClass, timeSlotId, profsOnDuty)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked =  (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_A_ID] = currClass;
                    boolean isSalsaStyling = isSalsaInA && currClass == salsaStylingId;
                    BestGuess someGuess = guessB(timeSlotId, classesSubset - classMask, SB, SD, BD, timeSlot, isSalsaInA, isSalsaStyling);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_A_ID] = currClass;
                        someGuess.isSalsaInA = isSalsaInA;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess guessB(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot, boolean isSalsaInA, boolean isSalsaStyling) {
            if (isSalsaStyling) {
                timeSlot[CLASSROOM_B_ID] = NO_CLASS; // class A and B will be used for the styling
                return endOfTimeSlot(timeSlotId, classesSubset, SB, SD, BD, timeSlot, true, false, salsa, null);
            } else if (isSalsaInA) { // salsa in A, guess bachata or discovery in B
                BestGuess bestGuess = IMPOSSIBLE;
                if (SB > 0) {
                    bestGuess = guessB(timeSlotId, classesSubset, SB - 1, SD, BD, timeSlot,
                            discovery.length, salsa, bachata, true, true, bachataProfsOnDuty, sameProfSalsaToBachata, bestGuess);
                }
                if (SD > 0) {
                    bestGuess = guessB(timeSlotId, classesSubset, SB, SD - 1, BD, timeSlot,
                            0, salsa, discovery, true, false, discoveryProfsOnDuty, sameProfSalsaToDiscovery, bestGuess);
                }
                return bestGuess;
            } else { // bachata in A, only option is discovery for B
                if (BD <= 0) {
                    return IMPOSSIBLE;
                }
                return guessB(timeSlotId, classesSubset, SB, SD, BD - 1, timeSlot,
                        0, bachata, discovery, false, false, discoveryProfsOnDuty, sameProfBachataToDiscovery, IMPOSSIBLE);
            }
        }
        BestGuess guessB(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot,
                         int offset, Set<Integer>[] As, Set<Integer>[] Bs, boolean isSalsaInA, boolean isBachataInB,
                         List<Integer>[] profsOnDuty, List<Integer>[] sameProfAtoB, BestGuess bestGuess) {
            for (int currClass = 0; currClass < Bs.length; ++currClass) {
                if (isProfAlreadyInTimeSlot(currClass, sameProfAtoB, timeSlot) || isProfOnDuty(currClass, timeSlotId, profsOnDuty)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked =  (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_B_ID] = currClass;
                    BestGuess someGuess = endOfTimeSlot(timeSlotId, classesSubset - classMask, SB, SD, BD, timeSlot, isSalsaInA, !isBachataInB, As, Bs);
                    if (someGuess.score < bestGuess.score || (someGuess.score == bestGuess.score && ThreadLocalRandom.current().nextFloat() < 0.5)) {
                        someGuess.timeSlot[CLASSROOM_B_ID] = currClass;
                        someGuess.isBachataInB = isBachataInB;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess endOfTimeSlot(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot, boolean isSalsaInA, boolean isDiscoveryInB, Set<Integer>[] As, Set<Integer>[] Bs) {
            float lookup = solve(timeSlotId + 1, classesSubset, SB, SD, BD);
            Set<Integer> B = timeSlot[CLASSROOM_B_ID] == NO_CLASS ? null : Bs[timeSlot[CLASSROOM_B_ID]];
            return lookup > SCORE_LIMIT ? IMPOSSIBLE : new BestGuess(
                    computeScore(timeSlotId, classesSubset, timeSlot, isSalsaInA, isDiscoveryInB, As[timeSlot[CLASSROOM_A_ID]], B) + lookup);
        }

        boolean isProfAlreadyInTimeSlot(int currClass, List<Integer>[] sameProfAtoB, int[] timeSlot) {
            return sameProfAtoB[timeSlot[CLASSROOM_A_ID]].stream().anyMatch(otherClassTheyGive -> currClass == otherClassTheyGive);
        }

        boolean isProfOnDuty(int currClass, int timeSlotId, List<Integer>[] profsOnDuty) {
            return profsOnDuty[currClass].stream().anyMatch(dutySlot -> dutySlot == timeSlotId);
        }

        private float computeAverage(int[] timeSlot, int[] aVotes, int[] bVotes) {
            if (timeSlot[CLASSROOM_B_ID] == NO_CLASS) {
                return aVotes[timeSlot[CLASSROOM_A_ID]];
            }
            float count = 2.0f;
            return (aVotes[timeSlot[CLASSROOM_A_ID]] + bVotes[timeSlot[CLASSROOM_B_ID]]) / count;
        }
        float computeScore(int timeSlotId, int classesSubset, int[] timeSlot, boolean isSalsaInA, boolean isDiscoveryInB, Set<Integer> interestedInA, Set<Integer> interestedInB) {
            Set<Integer> interestedInBoth = Set.of(), interestedInOneOrMore = new HashSet<>(interestedInA), onDutiesAndInterestedInOneOrMore = new HashSet<>(duties[timeSlotId]);
            if (interestedInB != null) {
                interestedInBoth = new HashSet<>(interestedInA);
                interestedInBoth.retainAll(interestedInB);
                interestedInOneOrMore.addAll(interestedInB);
            }
            /* Count profs as on duty, because they have no option just like the people in the kitchen */
            List<Integer> profsA = isSalsaInA ? salsaProfs[timeSlot[CLASSROOM_A_ID]] : bachataProfs[timeSlot[CLASSROOM_A_ID]];
            List<Integer> profsB = new ArrayList<>();
            if (timeSlot[CLASSROOM_B_ID] != NO_CLASS) {
                profsB = isDiscoveryInB ? discoveryProfs[timeSlot[CLASSROOM_B_ID]] : bachataProfs[timeSlot[CLASSROOM_B_ID]];
            }
            onDutiesAndInterestedInOneOrMore.addAll(profsA);
            onDutiesAndInterestedInOneOrMore.addAll(profsB);

            onDutiesAndInterestedInOneOrMore.retainAll(interestedInOneOrMore);
            final int RUMBA_CLASS = 3, REGGAE_CLASS = 0, STYLING = 4, STYLING_TIME = 4, PASITOS_ANGELICA = 3;
            float ANGELICA_W = 100.0f;
            boolean isAngelicaInA = (isSalsaInA && timeSlot[CLASSROOM_A_ID] == PASITOS_ANGELICA) ||
                    (isSalsaInA && timeSlot[CLASSROOM_A_ID] == STYLING);
            boolean isAngelicaInB = isDiscoveryInB && timeSlot[CLASSROOM_B_ID] == RUMBA_CLASS;

            float loss = interestedInBoth.stream().map(p -> pWeight[p]).reduce(0.0f, Float::sum);
            loss += onDutiesAndInterestedInOneOrMore.stream().map(p -> pWeight[p]).reduce(0.0f, Float::sum);
            if (isAngelicaInA) {
                Set<Integer> onDutiesAndInterstedInAngelica = new HashSet<>(onDutiesAndInterestedInOneOrMore);
                onDutiesAndInterstedInAngelica.retainAll(interestedInA);
                loss += onDutiesAndInterstedInAngelica.stream().map(p -> pWeight[p]).reduce(0.0f, Float::sum) * ANGELICA_W;
            } else if (isAngelicaInB) {
                Set<Integer> onDutiesAndInterstedInAngelica = new HashSet<>(onDutiesAndInterestedInOneOrMore);
                onDutiesAndInterstedInAngelica.retainAll(interestedInB);
                loss += onDutiesAndInterstedInAngelica.stream().map(p -> pWeight[p]).reduce(0.0f, Float::sum) * ANGELICA_W;
            }
            // Ilaria comes before Angelica
            if (isDiscoveryInB && timeSlot[CLASSROOM_B_ID] == RUMBA_CLASS) {
                int classMask = 1 << REGGAE_CLASS;
                if ((classesSubset & classMask) == 0) {
                    loss += 1000000;
                }
            }
            if (isSalsaInA && timeSlot[CLASSROOM_A_ID] == STYLING && timeSlotId != STYLING_TIME) {
                loss += 100000;
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

        float computeSimilarity(int[] timeSlot, int[] aVotes, int[] bVotes) {
            return computeAverage(timeSlot, aVotes, bVotes) / target * 100.0f;
        }
    }

    enum ClassLevel {
        INTERMEDIATE,
        ADVANCED
    }

    private static class BestGuess {
        float score;
        int[] timeSlot;
        boolean isSalsaInA, isBachataInB;
        BestGuess(float score) {
            this.score = score;
            timeSlot = new int[NUM_CLASSROOMS];
            timeSlot[0] = -1;
            timeSlot[1] = -1;
        }
    }
}
