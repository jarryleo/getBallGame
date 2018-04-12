//
// Created by Leo on 2018/4/12.
//
#include <jni.h>
#include <string>
#include <cstdlib>
#include <ctime>

using namespace std;

//球的总数
int getBallCount(int b[]) {
    return b[0] + b[1] + b[2];
}

//匹配特殊数字
bool match(int ball[], int a, int b, int c, int d, int e, int f, int g) {
    if (g > 2)return false;
    if ((ball[a] == d && ball[b] == e) || (ball[a] == e && ball[b] == d)) {
        ball[c] = f;
        return true;
    }
    if ((ball[a] == d && ball[c] == f) || (ball[a] == f && ball[c] == d)) {
        ball[b] = e;
        return true;
    }
    if ((ball[b] == e && ball[c] == f) || (ball[b] == f && ball[c] == e)) {
        ball[a] = d;
        return true;
    }
    return match(ball, b, c, a, d, e, f, g + 1);
}

//特殊数字
bool check(int ball[]) {
    bool flag = false;
    int an[] = {246, 145, 123, 111, 550, 440, 330, 220, 100};
    for (int i = 0; i < 9; i++) {
        int a = an[i] / 100;
        int b = an[i] / 10 % 10;
        int c = an[i] % 10;
        if (a + b + c >= getBallCount(ball)) continue;
        bool m = match(ball, 0, 1, 2, a, b, c, 0);
        if (m) {
            flag = m;
            break;
        }
    }
    return flag;
}

//电脑取球
void computer(int ball[]) {
    if (getBallCount(ball) <= 1) return;
    if (check(ball)) return;
    srand((unsigned) time(NULL));
    int r = rand() % 3;
    while (ball[r] == 0) {
        r = rand() % 3;
    }
    ball[r]--;
}

extern "C"
JNIEXPORT jintArray
JNICALL
Java_cn_leo_clib_GameBrainLib_think(
        JNIEnv *env,
        jobject obj,
        jintArray ball) {
    jint *elements = env->GetIntArrayElements(ball, false);
    computer(elements);
    env->SetIntArrayRegion(ball, 0, 3, elements);
    env->ReleaseIntArrayElements(ball,elements,0);
    return ball;
}


