package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private int seconds = 0;
    private boolean running;
    private boolean wasRunning;

    // default bundle is null

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }

    public void onClickStart (View view) {
        running = true;
    }

    public void onClickStop (View view) {
        running = false;
    }

    public void onClickReset (View view) {
        running = false;
        seconds = 0;
    }

    // stop when invisible
//    @Override
//    protected void onStop() {
//        super.onStop();
//        wasRunning = true;
//        running = false;
//    }
//
//    // run when visible
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (wasRunning)
//            running = true;
//    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning)
            running = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = true;
        running = false;
    }

    // todo: ROTATE DESTROYS THE ACTIVITY
    public void runTimer() {
        // this shit complex as fuck

        final TextView timeView = findViewById(R.id.time_view);

        // handler(): schedule code to run in the future
        final Handler handler = new Handler();

        // post(Runnable): run as soon as possible
        // postDelayed(Runnable, time): run as soon as possible after delay
        handler.post(new Runnable() {
            @Override
            public void run() {
                // format the seconds into h, m, s
                int hours = seconds / 3600, minutes = (seconds % 3600) / 60, secs = seconds % 60;
                String time = String.format(Locale.getDefault(), "%02d:%02d:%02d", hours, minutes, secs);

                // set text to text view
                timeView.setText(time);

                if (running) {
                    seconds++;
                }

                // run after 0 millis delay
                handler.postDelayed(this, 0);
            }
        });
    }

    // SAVE BEFORE DESTROY
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        // save values in a bundle (pair)

        // save seconds
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("seconds", seconds);

        // save running
        savedInstanceState.putBoolean("running", running);

        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
}

/* onCreate() When the activity is first created. Use it for normal
static setup, such as creating views. It also gives
you a Bundle that contains the previously saved
state of the activity.

onStart()

onRestart() When your activity has been stopped but just
before it gets started again.

onStart()

onStart() When your activity is becoming visible. It’s
followed by onResume() if the activity comes
into the foreground, or onStop() if the activity
is made invisible.

onResume() or
onStop()

onResume() When your activity is in the foreground. onPause()

onPause() When your activity is no longer in the foreground
because another activity is resuming. The next
activity isn’t resumed until this method finishes,
so any code in this method needs to be quick. It’s
followed by onResume() if the activity returns
to the foreground, or onStop() if it becomes
invisible.

onResume() or
onStop()

onStop() When the activity is no longer visible. This can be
because another activity is covering it, or because
this activity is being destroyed. It’s followed by
onRestart() if the activity becomes visible
again, or onDestroy() if the activity is being
destroyed.

onRestart() or
onDestroy()

onDestroy() When your activity is about to be destroyed or

because the activity is finishing.

None

onCreate(): Khi Activity được tạo lần đầu. Sử dụng nó để thiết lập các cấu hình tĩnh,
chẳng hạn như tạo các view. Nó cũng cung cấp cho bạn một Bundle chứa trạng thái đã
lưu trước đó của Activity.
Sử dụng cho: Thiết lập tĩnh, tạo view, lấy trạng thái đã lưu.
Gọi tiếp theo: onStart()

onStart(): Khi Activity bắt đầu hiển thị. Theo sau là onResume() nếu Activity
chuyển vào nền trước, hoặc onStop() nếu Activity bị ẩn đi.
Sử dụng cho: Chuẩn bị cho Activity hiển thị.
Gọi tiếp theo: onResume() hoặc onStop()

onRestart(): Khi Activity đã bị dừng nhưng chuẩn bị được bắt đầu lại.
Sử dụng cho: Thiết lập lại các tài nguyên hoặc kết nối.
Gọi tiếp theo: onStart()

onResume(): Khi Activity đang ở nền trước.
Sử dụng cho: Bắt đầu các tác vụ tương tác trực tiếp với người dùng.
Gọi tiếp theo: onPause()

onPause(): Khi Activity không còn ở nền trước vì một Activity khác đang được tiếp tục.
Activity tiếp theo không được tiếp tục cho đến khi phương thức này hoàn thành.
Theo sau là onResume() nếu Activity trở
lại nền trước, hoặc onStop() nếu nó trở nên vô hình.
Sử dụng cho: Lưu trạng thái hiện tại, dừng các tác vụ tạm thời.
Gọi tiếp theo: onResume() hoặc onStop()

onStop(): Khi Activity không còn hiển thị. Điều này có thể do một Activity khác đang
che phủ nó hoặc vì Activity này đang bị hủy. Theo sau là onRestart() nếu Activity trở nên
hiển thị lại, hoặc onDestroy() nếu Activity đang bị hủy.
Sử dụng cho: Dừng các tác vụ dài hạn, lưu trạng thái.
Gọi tiếp theo: onRestart() hoặc onDestroy()

onDestroy(): Khi Activity sắp bị hủy hoặc vì Activity đang kết thúc.
Sử dụng cho: Dọn dẹp tài nguyên.
Gọi tiếp theo: Không
*/