package edu.uark.uarkregisterapp.models.api.tasks;

import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;

import java.util.UUID;

import edu.uark.uarkregisterapp.R;
import edu.uark.uarkregisterapp.models.api.ApiResponse;
import edu.uark.uarkregisterapp.models.api.Product;
import edu.uark.uarkregisterapp.models.api.services.ProductService;
import edu.uark.uarkregisterapp.models.transition.ProductTransition;

public class SaveContactTask {
    private class InnerSaveProductTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            this.savingProductAlert.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Product product = (new Product()).
                    setId(productTransition.getId()).
                    setLookupCode(productTransition.getLookupCode()).
                    setCount(Integer.parseInt(String.valueOf(productTransition.getCount())));

            ApiResponse<Product> apiResponse = (
                    (product.getId().equals(new UUID(0, 0)))
                            ? (new ProductService()).createProduct(product)
                            : (new ProductService()).updateProduct(product)
            );

            if (apiResponse.isValidResponse()) {
                productTransition.setCount(apiResponse.getData().getCount());
                productTransition.setLookupCode(apiResponse.getData().getLookupCode());
            }

            return apiResponse.isValidResponse();
        }

        @Override
        protected void onPostExecute(Boolean successfulSave) {
            String message;

            savingProductAlert.dismiss();

            if (successfulSave) {
                message = getContext().getResources().getString(R.string.alert_dialog_product_save_success);
            } else {
                message = getContext().getResources().getString(R.string.alert_dialog_product_save_failure);
            }

            new AlertDialog.Builder(getContext()).
                    setMessage(message).
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

        private AlertDialog savingProductAlert;

        private InnerSaveProductTask() {
            this.savingProductAlert = new AlertDialog.Builder(getContext()).
                    setMessage(R.string.alert_dialog_product_save).
                    create();
        }
    }

    public Context getContext(){
        return context;
    }
    public void setContext(Context context){
        this.context = context;
    }

    public ProductTransition getProductTransition() {
        return productTransition;
    }

    public void setProductTransition(ProductTransition productTransition) {
        this.productTransition = productTransition;
    }

    private Context context;
    private ProductTransition productTransition;
}
