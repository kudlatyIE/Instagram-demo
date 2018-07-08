package ie.droidfactory.instagramdemo.ModelView;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import ie.droidfactory.instagramdemo.model.UserSelf;
import ie.droidfactory.instagramdemo.network.ApiUtils;
import ie.droidfactory.instagramdemo.network.ServiceFactory;
import ie.droidfactory.instagramdemo.network.ServiceInstagram;
import ie.droidfactory.instagramdemo.utils.MySharedPref;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UserSelfViewModel extends ViewModel{

    private final static String TAG = UserSelfViewModel.class.getSimpleName();

    private MutableLiveData<UserSelf> mutableLiveDataUserSelf;

    public LiveData<UserSelf> getUserSelf(ServiceInstagram service, String accessToken, boolean refreshData){
        if (null==mutableLiveDataUserSelf){
            mutableLiveDataUserSelf = new MutableLiveData<>();
            loadDataFromInstagram(service, accessToken);
        }else if(refreshData) loadDataFromInstagram(service, accessToken);
        return mutableLiveDataUserSelf;
    }

    private void loadDataFromInstagram(ServiceInstagram service, String acessToken) {
        Call<UserSelf> call = service.getUserSelf(acessToken);
        call.enqueue(new Callback<UserSelf>() {
            @Override
            public void onResponse(@NonNull Call<UserSelf> call, @NonNull Response<UserSelf> response) {
                if(response.body()!=null){
                    mutableLiveDataUserSelf.setValue(response.body());
                    Log.d(TAG, "http response code: "+response.body().getMeta().getCode());
                }

            }
            @Override
            public void onFailure(@NonNull Call<UserSelf> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}
