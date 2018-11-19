package edu.uark.uarkregisterapp.models.api.tasks;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.util.List;

import edu.uark.uarkregisterapp.R;
import edu.uark.uarkregisterapp.adapters.ProductListAdapter;
import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.services.ProductService;

public class RetrieveContactsTask {
    private class InnerRetrieveProductsTask extends AsyncTask<Void, Void, ApiResponse<List<Product>>> {
        private InnerRetrieveProductsTask() {
            this.loadingProductsAlert = new AlertDialog.Builder(getContext()).
                    setMessage(R.string.alert_dialog_products_loading).
                    create();
        }

        @Override
        protected void onPreExecute() {
            this.loadingProductsAlert.show();
        }

        @Override
        protected ApiResponse<List<Product>> doInBackground(Void... params) {
            ApiResponse<List<Product>> apiResponse = (new ProductService()).getProducts();

            if (apiResponse.isValidResponse()) {
                products.clear();
                products.addAll(apiResponse.getData());
            }

            return apiResponse;
        }

        @Override
        protected void onPostExecute(ApiResponse<List<Product>> apiResponse) {
            if (apiResponse.isValidResponse()) {
                productListAdapter.notifyDataSetChanged();
            }

            this.loadingProductsAlert.dismiss();

            if (!apiResponse.isValidResponse()) {
                new AlertDialog.Builder(getContext()).
                        setMessage(R.string.alert_dialog_products_load_failure).
                        setPositiveButton(
                                R.string.button_dismiss,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.dismiss();
                                    }
                                }
                        ).
                        create().
                        show();
            }
        }

        private AlertDialog loadingProductsAlert;
    }
    public List<Product> getProducts(){
        return products;
    }

    public ProductListAdapter getProductListAdapter(){
        return productListAdapter;
    }

    public Context getContext(){
        return context;
    }
    public void setContext(Context context){
        this.context = context;
    }

    private Context context;
    private List<Product> products;
    private ProductListAdapter productListAdapter;
}
