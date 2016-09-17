package com.neogenesis.pfaat.seqspace;


import Jama.*;

import com.neogenesis.pfaat.*;


/**
 * SeqSpaceEncoder implementation for a AADist-based encoding.
 *
 * @author $Author: xih $
 * @version $Revision: 1.4 $, $Date: 2002/10/11 18:30:38 $ */
public class AAPhysicoChemicalEncoder implements SeqSpaceEncoder {
    // AADist matrix
    private static final double[][] AADist = {
            // A   R   N   D   C   Q   E   G   H   I   L   K   M   F   P   S   T   W   Y   V   B   Z   X  -
            {4.08224197805054, -2.92538047971271, 1.0129777891375, 1.65899496637551, 2.24503583837107, -4.40418170488834E-02, -0.198541032751097, 5.87617871586288, -1.31715652568077, -0.893365012494206, -0.9545498715941, -1.78505462907624, -1.65877627431065, -3.3151566052964, 2.89888685788707, 3.04401470240354, 0.798025801011954, -5.23656812683216, -3.9490233095569, 0.661257035254041, -2, -1, -1, -5},
            {-2.92538047971271, 8.57314126250413, 0.79439510116095, -2.49833532489179, -2.6418400112894, 1.30852868871953, -1.19386051153707, -3.87309298553816, 4.40666252833143, -1.32577726770597, -1.55248921067376, 4.25496223464296, 0.595971249560325, 1.4082536928384, -5.34725980774206, -1.58038272841964, -0.86435084466335, 2.20800546386257, 1.97823494133099, -1.72538599077739, -1, 0, -1, -5},
            {1.0129777891375, 0.79439510116095, 4.21347377343441, 2.82927071832763, 0.447147714177465, 2.37057817479339, 2.09494250410825, 3.12123784785558, 1.36395306951344, -3.63422804231923, -4.05216201185923, -1.49354843287008, -1.60840451577739, -1.66863181134667, -3.56563215079626, 3.17323601643439, 0.969734545897432, -3.5337143223422, -0.770606776368217, -2.06401919116116, 4, 0, -1, -5},
            {1.65899496637551, -2.49833532489179, 2.82927071832763, 5.14758684889641, 3.92636799112205, 1.425900980501, 3.21376652069242, 3.59734654841615, 0.36297583239084, -2.874340956966, -3.2754479812347, -3.98827382315147, -1.80221909599327, -2.1752723051789, -1.77046913300076, 2.66732990558489, 0.285174929811573, -3.86571815009584, -1.46660785004324, -1.39803062156251, 5, 1, -1, -5},
            {2.24503583837107, -2.6418400112894, 0.447147714177465, 3.92636799112205, 9.07458815853367, -0.557488802308143, 0.916280145977753, 4.35816426369068, -0.283419600785342, -2.91335507398461, -3.32104874189607, -0.667764784063678, -3.0815434891334, -3.75717540261059, 2.83583724992668, 1.57169247952616, 2.49793419692966, -5.69482343868578, -3.63892363077061, -1.31566506272755, -3, -3, -2, -5},
            {-4.40418170488834E-02, 1.30852868871953, 2.37057817479339, 1.425900980501, -0.557488802308143, 1.8108599271787, 1.51587275964313, 0.638843536654146, 1.22788282807344, -2.09582743503424, -2.38837771716102, -0.528096027292031, -0.635153948350424, -0.635380511273359, -2.44776942315623, 1.13881703349556, -4.51429788327114E-02, -0.745289561060684, 8.70350972516491E-02, -1.40175080479284, 0, 4, -1, -5},
            {-0.198541032751097, -1.19386051153707, 2.09494250410825, 3.21376652069242, 0.916280145977753, 1.51587275964313, 2.93540249784025, 0.388385213353853, 0.446554160014899, -1.74870800847768, -2.03542476064006, -2.60496193006754, -0.519348836723463, -0.356438904622664, -2.33268204147118, 0.948458588449675, -0.266502978935398, -0.495084672423207, 0.510175358787682, -1.21828407121857, 1, 5, -1, -5},
            {5.87617871586288, -3.87309298553816, 3.12123784785558, 3.59734654841615, 4.35816426369068, 0.638843536654146, 0.388385213353853, 9.64944173678717, -1.35854383994174, -3.04995756398497, -3.32161256363916, -2.96596351213641, -3.35426309062772, -5.2012664100797, 2.73718167326765, 5.62306015953581, 1.94708474182455, -8.98356088889483, -5.58644169863714, -0.242221883768621, -1, -2, -2, -5},
            {-1.31715652568077, 4.40666252833143, 1.36395306951344, 0.36297583239084, -0.283419600785342, 1.22788282807344, 0.446554160014899, -1.35854383994174, 3.19958540896295, -1.70933160900047, -1.9643629716374, 1.06553753384927, -7.27653229752875E-02, 0.361834507977502, -4.54566183494963, -0.142434207636374, -0.463213735647212, 0.108124247411308, 0.773634420234362, -1.45985488850523, 0, 0, -1, -5},
            {-0.893365012494206, -1.32577726770597, -3.63422804231923, -2.874340956966, -2.91335507398461, -2.09582743503424, -1.74870800847768, -3.04995756398497, -1.70933160900047, 4.4319947146763, 4.99038930003055, 0.112558754556766, 2.42547760045762, 2.43237046220372, 2.06293242412315, -2.51793744216568, -1.63757568535081, 3.83050458325659, 1.42352525953266, 2.69065099864653, -4, -3, -1, -5},
            {-0.9545498715941, -1.55248921067376, -4.05216201185923, -3.2754479812347, -3.32104874189607, -2.38837771716102, -2.03542476064006, -3.32161256363916, -1.9643629716374, 4.99038930003055, 5.64046581822593, 0.151810511348658, 2.75622693866737, 2.71011130430589, 2.29208191550703, -2.72804338762182, -1.69146563408566, 4.16133388886707, 1.52398101448819, 3.05858416060233, -4, -3, -1, -5},
            {-1.78505462907624, 4.25496223464296, -1.49354843287008, -3.98827382315147, -0.667764784063678, -0.528096027292031, -2.60496193006754, -2.96596351213641, 1.06553753384927, 0.112558754556766, 0.151810511348658, 5.97489444458638, 0.402809760084225, 0.361445554402334, 1.00860711498226, -2.25990905966593, 1.78429371026442, 1.53795866137572, 7.80351140608115E-02, -0.439341195830404, 0, 1, -1, -5},
            {-1.65877627431065, 0.595971249560325, -1.60840451577739, -1.80221909599327, -3.0815434891334, -0.635153948350424, -0.519348836723463, -3.35426309062772, -7.27653229752875E-02, 2.42547760045762, 2.75622693866737, 0.402809760084225, 2.11727333599376, 2.20352281080838, -1.11683849150574, -1.85040787806282, -1.00755115799374, 3.23497692538909, 1.77338873168339, 1.19762474880975, -3, -1, -1, -5},
            {-3.3151566052964, 1.4082536928384, -1.66863181134667, -2.1752723051789, -3.75717540261059, -0.635380511273359, -0.356438904622664, -5.2012664100797, 0.361834507977502, 2.43237046220372, 2.71011130430589, 0.361445554402334, 2.20352281080838, 3.99464674274065, -2.22749067348053, -2.71330697864442, -1.76331409813624, 5.59020764947562, 4.16003726803917, 0.591003707877816, -4, -4, -2, -5},
            {2.89888685788707, -5.34725980774206, -3.56563215079626, -1.77046913300076, 2.83583724992668, -2.44776942315623, -2.33268204147118, 2.73718167326765, -4.54566183494963, 2.06293242412315, 2.29208191550703, 1.00860711498226, -1.11683849150574, -2.22749067348053, 11.0321952930466, -0.787908333252757, 0.796756534812327, -0.50042274593281, -3.02271978494227, 2.00037535667745, -2, -1, -2, -5},
            {3.04401470240354, -1.58038272841964, 3.17323601643439, 2.66732990558489, 1.57169247952616, 1.13881703349556, 0.948458588449675, 5.62306015953581, -0.142434207636374, -2.51793744216568, -2.72804338762182, -2.25990905966593, -1.85040787806282, -2.71330697864442, -0.787908333252757, 4.03022837136253, 1.2837482879381, -5.55180103471336, -2.65665117880655, -0.691803315741309, 0, 0, -1, -5},
            {0.798025801011954, -0.86435084466335, 0.969734545897432, 0.285174929811573, 2.49793419692966, -4.51429788327114E-02, -0.266502978935398, 1.94708474182455, -0.463213735647212, -1.63757568535081, -1.69146563408566, 1.78429371026442, -1.00755115799374, -1.76331409813624, 0.796756534812327, 1.2837482879381, 3.54899138842613, -3.42001612582389, -2.02656700004773, -0.726043897399383, 0, -1, 0, -5},
            {-5.23656812683216, 2.20800546386257, -3.5337143223422, -3.86571815009584, -5.69482343868578, -0.745289561060684, -0.495084672423207, -8.98356088889483, 0.108124247411308, 3.83050458325659, 4.16133388886707, 1.53795866137572, 3.23497692538909, 5.59020764947562, -0.50042274593281, -5.55180103471336, -3.42001612582389, 10.422622404078, 6.1362437102412, 0.797021532847572, -5, -2, -3, -5},
            {-3.9490233095569, 1.97823494133099, -0.770606776368217, -1.46660785004324, -3.63892363077061, 8.70350972516491E-02, 0.510175358787682, -5.58644169863714, 0.773634420234362, 1.42352525953266, 1.52398101448819, 7.80351140608115E-02, 1.77338873168339, 4.16003726803917, -3.02271978494227, -2.65665117880655, -2.02656700004773, 6.1362437102412, 5.01839303471528, -0.345142721192735, -3, -2, -1, -5},
            {0.661257035254041, -1.72538599077739, -2.06401919116116, -1.39803062156251, -1.31566506272755, -1.40175080479284, -1.21828407121857, -0.242221883768621, -1.45985488850523, 2.69065099864653, 3.05858416060233, -0.439341195830404, 1.19762474880975, 0.591003707877816, 2.00037535667745, -0.691803315741309, -0.726043897399383, 0.797021532847572, -0.345142721192735, 2.03102610396223, -4, -3, -1, -5},
            {-2, -1, 4, 5, -3, 0, 1, -1, 0, -4, -4, 0, -3, -4, -2, 0, 0, -5, -3, -4, 5, 2, -1, -5},
            {-1, 0, 0, 1, -3, 4, 5, -2, 0, -3, -3, 1, -1, -4, -1, 0, -1, -2, -2, -3, 2, 5, -1, -5},
            {-1, -1, -1, -1, -2, -1, -1, -2, -1, -1, -1, -1, -1, -2, -2, -1, 0, -3, -1, -1, -1, -1, -1, -5},
            {-5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, -5, 1}
        };

    public double getPairScore(AminoAcid aa1, AminoAcid aa2) {
        return AADist[aa1.getIndex()][aa2.getIndex()];
    }

    public double[][] getMatrix() {
        return AADist;
    }
}
