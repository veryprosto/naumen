package ru.veryprosto.naumenTask;

public class RouteFinderImpl implements RouteFinder {
    public static boolean win;
    public static int[][] arr;

    public static void buildWay(int x, int y, int lastNum) {
        if (checkBlockByNum(x + 1, y, lastNum)) {
            lastNum--;
            arr[y][x] = arr[y][x] == -9 ? -9 : -2;
            buildWay(x + 1, y, lastNum);
        }
        if (checkBlockByNum(x - 1, y, lastNum)) {
            lastNum--;
            arr[y][x] = arr[y][x] == -9 ? -9 : -2;
            buildWay(x - 1, y, lastNum);
        }
        if (checkBlockByNum(x, y + 1, lastNum)) {
            lastNum--;
            arr[y][x] = arr[y][x] == -9 ? -9 : -2;
            buildWay(x, y + 1, lastNum);
        }
        if (checkBlockByNum(x, y - 1, lastNum)) {
            lastNum--;
            arr[y][x] = arr[y][x] == -9 ? -9 : -2;
            buildWay(x, y - 1, lastNum);
        }
    }

    public static boolean checkRightBlock(int x, int y) {
        return x >= 0 && y >= 0 && y < arr.length && x < arr[y].length;
    }

    public static boolean checkBlockByNum(int x, int y, int num) {
        if (checkRightBlock(x, y)) {
            return arr[y][x] == num;
        }
        return false;
    }


    public static void checkAndReMarkNeighbors(int x, int y, int num) {
        win = checkBlockByNum(x + 1, y, -9) || checkBlockByNum(x - 1, y, -9) || checkBlockByNum(x, y + 1, -9) || checkBlockByNum(x, y - 1, -9);
        if (win) return;

        if (checkBlockByNum(x - 1, y, 0)) arr[y][x - 1] = num;
        if (checkBlockByNum(x + 1, y, 0)) arr[y][x + 1] = num;
        if (checkBlockByNum(x, y - 1, 0)) arr[y - 1][x] = num;
        if (checkBlockByNum(x, y + 1, 0)) arr[y + 1][x] = num;
    }

    public static int getLastNum(int x, int y) {
        int sss = -100;
        if (!checkBlockByNum(x + 1, y, -8) && !checkBlockByNum(x + 1, y, 0) && checkRightBlock(x + 1, y))
            sss = arr[y][x + 1];
        if (!checkBlockByNum(x - 1, y, -8) && !checkBlockByNum(x - 1, y, 0) && checkRightBlock(x - 1, y))
            sss = arr[y][x - 1];
        if (!checkBlockByNum(x, y + 1, -8) && !checkBlockByNum(x, y + 1, 0) && checkRightBlock(x, y + 1))
            sss = arr[y + 1][x];
        if (!checkBlockByNum(x, y - 1, -8) && !checkBlockByNum(x, y - 1, 0) && checkRightBlock(x, y - 1))
            sss = arr[y - 1][x];
        return sss;
    }

    public static int[][] charArrToIntArr(char[][] charArr) {
        int[][] intArr = new int[charArr.length][charArr[0].length];
        for (int y = 0; y < charArr.length; y++) {
            for (int x = 0; x < charArr[0].length; x++) {
                char cV = charArr[y][x];
                int iV;
                switch (cV) {
                    case '.':
                        iV = 0;
                        break;
                    case '@':
                        iV = 1;
                        break;
                    case 'X':
                        iV = -9;
                        break;
                    default:
                        iV = -8;
                        break;
                }
                intArr[y][x] = iV;
            }
        }
        return intArr;
    }

    public static char[][] intArrToCharArr(int[][] intArr) {
        char[][] charArr = new char[intArr.length][intArr[0].length];
        for (int y = 0; y < intArr.length; y++) {
            for (int x = 0; x < intArr[0].length; x++) {
                int iV = intArr[y][x];
                char cV;
                switch (iV) {
                    case 0:
                        cV = '.';
                        break;
                    case 1:
                        cV = '@';
                        break;
                    case -9:
                        cV = 'X';
                        break;
                    case -2:
                        cV = '+';
                        break;
                    default:
                        cV = '#';
                        break;
                }
                charArr[y][x] = cV;
            }
        }
        return charArr;
    }


    @Override
    public char[][] findRoute(char[][] map) {
        arr = charArrToIntArr(map);
        int num = 1;
        while (!win) {
            for (int y = 0; y < map.length; y++) {
                for (int x = 0; x < map[y].length; x++) {
                    if (arr[y][x] == num) checkAndReMarkNeighbors(x, y, num + 1);
                }
            }
            num++;
        }
        int finishX = 0, finishY = 0;

        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length; x++) {
                if (arr[y][x] == -9) {
                    finishX = x;
                    finishY = y;
                }
            }
        }

        int lastNum = getLastNum(finishX, finishY);

        buildWay(finishX, finishY, lastNum);


        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length; x++) {
                if (arr[y][x] != -8 && arr[y][x] != -9 && arr[y][x] != -2 && arr[y][x] != 1) {
                    arr[y][x] = 0;
                }
            }
        }
        return intArrToCharArr(arr);
    }
}
