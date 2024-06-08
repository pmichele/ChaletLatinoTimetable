import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Michele Pettinato
 */
public class Main {

    static final int TIME_SLOTS = 7, CLASSROOM_A_ID = 0, CLASSROOM_B_ID = 1, NUM_CLASSROOMS = 2, END_OF_TIME_SLOT = NUM_CLASSROOMS, NO_CLASS = -1;
    static final float INFINITY = Integer.MAX_VALUE, SCORE_LIMIT = 1_000_000_000.0f;

    static String[] salsaNames = new String[] {
            "Salsa fusion by PA y Este",
            "Arm work Salsa Fusion by Ilaria y Harshit",
            "Salsa all styles by Larissa y Caro",
            "Pasitos salsa con Afro by Angelica",
            "Men/Lady styling (man vs girl) by Angelica y PA"
    };
    static String[] bachataNames = new String[] {
            "Bachata moderne by Patrick y Sofia",
            "Bachata musicalité et breaks partner works by Patrick y ??",
            "Bachata smooth transition by Thanu y Sofia",
            "Bachata sensual couple connection by Thanu y Larissa"
    };
    static String[] discoveryNames = new String[] {
            "Reggaetton fusion by Ilaria",
            "Hip Hop by Ilaria",
            "Zouk Flow Introduction by Thanu y Julie",
            "Rumba by Angelica"
    };

    static int[] salsaVotes = new int[] {23, 24, 18, 30, 34};
    static int[] bachataVotes = new int[] {15, 16, 19, 20};
    static int[] discoveryVotes = new int[] {28, 18, 16, 29};

//        static int[] salsaVotes = new int[] {35, 0, 35, 40, 35};
//    static int[] bachataVotes = new int[] {35, 35, 35, 70};
//    static int[] discoveryVotes = new int[] {35, 35, 35, 35};
    static int salsaStylingId = 4;

    public static void main(String[] args) {

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

        // Note : +1 is because of man/lady styling
        if ((salsaVotes.length + 1) + bachataVotes.length + discoveryVotes.length != NUM_CLASSROOMS * TIME_SLOTS) {
            throw new RuntimeException("Not Enough classes for the time slots");
        }
        int numClasses = salsaVotes.length + bachataVotes.length + discoveryVotes.length;
        System.out.println("--- Number of classes " + numClasses);


        /* Solve */
        Optimizer optimizer = new Optimizer(salsaVotes, bachataVotes, discoveryVotes,
                sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery,
                salsaProfsOnDuty, bachataProfsOnDuty, discoveryProfsOnDuty, salsaStylingId);
        float bestScore = optimizer.solve(0, (1 << numClasses) - 1, 2, 2, 2);
        System.out.println("--- Average loss " + (bestScore / TIME_SLOTS / optimizer.target * 100.0f) + "%");
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
        int[] salsaVotes, bachataVotes, discoveryVotes;
        List<Integer>[] sameProfSalsaToBachata, sameProfSalsaToDiscovery, sameProfBachataToDiscovery;
        List<Integer>[] salsaProfsOnDuty, bachataProfsOnDuty, discoveryProfsOnDuty;
        final BestGuess IMPOSSIBLE = new BestGuess(INFINITY);

        final int salsaStylingId;
        final float target;

        Optimizer(int[] salsaVotes, int[] bachataVotes, int[] discoveryVotes, List<Integer>[] sameProfSalsaToBachata,
                  List<Integer>[] sameProfSalsaToDiscovery, List<Integer>[] sameProfBachataToDiscovery,
                  List<Integer>[] salsaProfsOnDuty, List<Integer>[] bachataProfsOnDuty, List<Integer>[] discoveryProfsOnDuty,
                  int salsaStylingId) {
            this.salsaVotes = salsaVotes;
            this.bachataVotes = bachataVotes;
            this.discoveryVotes = discoveryVotes;
            this.sameProfSalsaToBachata = sameProfSalsaToBachata;
            this.sameProfSalsaToDiscovery = sameProfSalsaToDiscovery;
            this.sameProfBachataToDiscovery = sameProfBachataToDiscovery;
            this.salsaProfsOnDuty = salsaProfsOnDuty;
            this.bachataProfsOnDuty = bachataProfsOnDuty;
            this.discoveryProfsOnDuty = discoveryProfsOnDuty;
            this.salsaStylingId = salsaStylingId;
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
            BestGuess bestGuess = guessA(timeSlotId, classesSubset, SB, SD, BD, timeSlot, bachataVotes.length + discoveryVotes.length,
                    salsaVotes.length, salsaProfsOnDuty, true, IMPOSSIBLE);
            // guess Bachata in classroom A
            return guessA(timeSlotId, classesSubset, SB, SD, BD, timeSlot, discoveryVotes.length,
                    bachataVotes.length, bachataProfsOnDuty, false, bestGuess);
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
                return endOfTimeSlot(timeSlotId, classesSubset, SB, SD, BD, timeSlot, salsaVotes, null);
            } else if (isSalsaInA) { // salsa in A, guess bachata or discovery in B
                BestGuess bestGuess = IMPOSSIBLE;
                if (SB > 0) {
                    bestGuess = guessB(timeSlotId, classesSubset, SB - 1, SD, BD, timeSlot,
                            discoveryVotes.length, salsaVotes, bachataVotes, true, bachataProfsOnDuty, sameProfSalsaToBachata, bestGuess);
                }
                if (SD > 0) {
                    bestGuess = guessB(timeSlotId, classesSubset, SB, SD - 1, BD, timeSlot,
                            0, salsaVotes, discoveryVotes, false, discoveryProfsOnDuty, sameProfSalsaToDiscovery, bestGuess);
                }
                return bestGuess;
            } else { // bachata in A, only option is discovery for B
                if (BD <= 0) {
                    return IMPOSSIBLE;
                }
                return guessB(timeSlotId, classesSubset, SB, SD, BD - 1, timeSlot,
                        0, bachataVotes, discoveryVotes, false, discoveryProfsOnDuty, sameProfBachataToDiscovery, IMPOSSIBLE);
            }
        }

        BestGuess guessB(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot,
                         int offset, int[] aVotes, int[] bVotes, boolean isBachataInB,
                         List<Integer>[] profsOnDuty, List<Integer>[] sameProfAtoB, BestGuess bestGuess) {
            for (int currClass = 0; currClass < bVotes.length; ++currClass) {
                if (isProfAlreadyInTimeSlot(currClass, sameProfAtoB, timeSlot) || isProfOnDuty(currClass, timeSlotId, profsOnDuty)) {
                    continue;
                }
                int classMask = 1 << (offset + currClass);
                boolean isClassNotPicked =  (classesSubset & classMask) > 0;
                if (isClassNotPicked) {
                    timeSlot[CLASSROOM_B_ID] = currClass;
                    BestGuess someGuess = endOfTimeSlot(timeSlotId, classesSubset - classMask, SB, SD, BD, timeSlot, aVotes, bVotes);
                    if (someGuess.score < bestGuess.score) {
                        someGuess.timeSlot[CLASSROOM_B_ID] = currClass;
                        someGuess.isBachataInB = isBachataInB;
                        bestGuess = someGuess;
                    }
                }
            }
            return bestGuess;
        }

        BestGuess endOfTimeSlot(int timeSlotId, int classesSubset, int SB, int SD, int BD, int[] timeSlot, int[] aVotes, int[] bVotes) {
            float lookup = solve(timeSlotId + 1, classesSubset, SB, SD, BD);
            return lookup > SCORE_LIMIT ? IMPOSSIBLE : new BestGuess(computeScore(timeSlot, timeSlotId, aVotes, bVotes) + lookup);
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
        float computeScore(int[] timeSlot, int timeSlotId, int[] aVotes, int[] bVotes) {
            float meanAbsoluteError = Math.abs(computeAverage(timeSlot, aVotes, bVotes) - target);
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
            return meanAbsoluteError;
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
        }
    }
}
