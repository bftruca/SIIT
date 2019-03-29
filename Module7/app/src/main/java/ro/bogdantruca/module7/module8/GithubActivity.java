package ro.bogdantruca.module7.module8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import ro.bogdantruca.module7.R;

public class GithubActivity extends AppCompatActivity {

    private UsersRepository mUsersRepository;

    private TextView mTextViewGithubUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_github);

        mTextViewGithubUser = findViewById(R.id.textView5);

        mUsersRepository = UsersRepository.getInstance();

        mUsersRepository.getUsers(new OnGetUsersCallback() {
            @Override
            public void onSuccess(List<User> users) {
                for (User user : users) {
                    mTextViewGithubUser.append(user.toString() + "");
                }

                Logging.show("Github users = ", users.toString());
            }

            @Override
            public void onError() {
                Logging.show("error Github users = ", "check the code :D ");
            }
        });
    }
}
