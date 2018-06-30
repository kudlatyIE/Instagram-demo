package ie.droidfactory.instagramdemo.ModelView;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import ie.droidfactory.instagramdemo.model.UserSelf;
import ie.droidfactory.instagramdemo.network.ApiUtils;
import ie.droidfactory.instagramdemo.network.ServiceFactory;
import ie.droidfactory.instagramdemo.network.ServiceInstagram;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserSelfViewModel extends ViewModel{

    private final static String TAG = UserSelfViewModel.class.getSimpleName();


    private MutableLiveData<UserSelf> mutableLiveDataUserSelf;

    public LiveData<UserSelf> loadUserSelf(String acessToken){
        if (null==mutableLiveDataUserSelf){
            mutableLiveDataUserSelf = new MutableLiveData<>();
            getUserStats(acessToken);
        }
        return mutableLiveDataUserSelf;
    }

    private void getUserStats(String acessToken) {
        ServiceInstagram service = ServiceFactory.createService(ServiceInstagram.class, ApiUtils.ENDPOINT_SELF);
        Call<UserSelf> call = service.getUserSelf(acessToken);

        call.enqueue(new Callback<UserSelf>() {
            @Override
            public void onResponse(Call<UserSelf> call, Response<UserSelf> response) {
                Log.d(TAG, "http response code: "+response.body().getMeta().getCode());
                mutableLiveDataUserSelf.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserSelf> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
