package com.androidtest.fup.dextest;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by fup on 2017/12/26.
 */
public class TaxActivity extends AppCompatActivity {

    private EditText totalEdit;
    private EditText baseEdit;
    private EditText retireEdit;
    private EditText medicalEdit;
    private EditText jobLoseEdit;
    private EditText pubFundsEdit;
    private EditText pubFundsRatio;
    private EditText salaryEdit;
    private EditText salaryTotalEdit;
    private EditText taxEdit;
    private EditText companyPayEdit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tax);
        initView();
    }

    void initView() {
        View total = findViewById(R.id.total);
        getNameTextView(total).setText("总工资");
        totalEdit = getValueEditText(total);

        View base = findViewById(R.id.base);
        getNameTextView(base).setText("自定义社保基数");
        baseEdit = getValueEditText(base);

        View retire = findViewById(R.id.retire);
        getNameTextView(retire).setText("养老8%");
        retireEdit = getValueEditText(retire);

        View medical = findViewById(R.id.medical);
        getNameTextView(medical).setText("医疗2%");
        medicalEdit = getValueEditText(medical);

        View jobLose = findViewById(R.id.job_lose);
        getNameTextView(jobLose).setText("失业1%");
        jobLoseEdit = getValueEditText(jobLose);

        View pubFunds = findViewById(R.id.pub_funds);
        getNameTextView(pubFunds).setText("自缴公积金");
        pubFundsEdit = getValueEditText(pubFunds);
        pubFundsRatio = getValueEditText(pubFunds, R.id.pub_funds_ratio);

        View salary = findViewById(R.id.salary);
        getNameTextView(salary).setText("税后");
        salaryEdit = getValueEditText(salary);

        View totalSalary = findViewById(R.id.total_salary);
        getNameTextView(totalSalary).setText("加公积金总收入");
        salaryTotalEdit = getValueEditText(totalSalary);

        View tax = findViewById(R.id.tax);
        getNameTextView(tax).setText("个税");
        taxEdit = getValueEditText(tax);

        View companyPay = findViewById(R.id.company_pay);
        getNameTextView(companyPay).setText("公司总支出");
        companyPayEdit = getValueEditText(companyPay);
    }

    public void onButtonClick(View v) {
        float total = getValueFromEditText(totalEdit);
        if(total <= 0) {
            return;
        }
        float base = getValueFromEditText(baseEdit);
        float retire = getValueFromEditText(retireEdit);
        float medical = getValueFromEditText(medicalEdit);
        float jobLose = getValueFromEditText(jobLoseEdit);
        float pubFunds = getValueFromEditText(pubFundsEdit);
        float pubRatio = getValueFromEditText(pubFundsRatio);

        if(pubFunds <= 0) {
            pubFunds = total * pubRatio/100;
            pubFundsEdit.setText("" + pubFunds);
        }
        if(retire >0 || medical >0 || jobLose >0) {//社保按照自定义计算
            float salary = total - retire - medical - jobLose - pubFunds;
            float salaryAfterTax = salaryAfterTax(salary);
            salaryEdit.setText("" + salaryAfterTax);
            taxEdit.setText("" + (salary - salaryAfterTax));
            salaryTotalEdit.setText("" + (salaryAfterTax + pubFunds*2));

            companyPayEdit.setText("" + (total + retire*14/8 + medical*6.7f/2 + jobLose*2 + pubFunds));
            return;
        }
        if(base > 0) {//自定义基数
            retire = base*0.08f;
            medical = base*0.02f;
            jobLose = base*0.01f;
            float salary = total - retire - medical - jobLose - pubFunds;

            float salaryAfterTax = salaryAfterTax(salary);
            salaryEdit.setText("" + salaryAfterTax);
            taxEdit.setText("" + (salary - salaryAfterTax));
            salaryTotalEdit.setText("" + (salaryAfterTax + pubFunds*2));


            companyPayEdit.setText("" + (total + retire*14/8 + medical*6.7f/2 + jobLose*2 + pubFunds));
            retireEdit.setText("" + retire);
            medicalEdit.setText("" + medical);
            jobLoseEdit.setText("" + jobLose);

            return;
        }

        //正常基数
        retire = total*0.08f;
        medical = total*0.02f;
        jobLose = total*0.01f;
        float salary = total - retire - medical - jobLose - pubFunds;

        float salaryAfterTax = salaryAfterTax(salary);
        salaryEdit.setText("" + salaryAfterTax);
        taxEdit.setText("" + (salary - salaryAfterTax));
        salaryTotalEdit.setText("" + (salaryAfterTax + pubFunds*2));

        companyPayEdit.setText("" + (total + retire*14/8 + medical*6.7f/2 + jobLose*2 + pubFunds));
        retireEdit.setText("" + retire);
        medicalEdit.setText("" + medical);
        jobLoseEdit.setText("" + jobLose);
    }

    TextView getNameTextView(View v) {
        return (TextView) v.findViewById(R.id.item_name);
    }
    EditText getValueEditText(View v) {
        return (EditText) v.findViewById(R.id.item_value);
    }
    EditText getValueEditText(View v, int id) {
        return (EditText) v.findViewById(id);
    }

//    float getValue(EditText v, float total, float defaultRatio) {
//        float value = getValueFromEditText(v);
//        if(value <= 0) {
//            return total*defaultRatio;
//        }
//    }

    float getValueFromEditText(EditText v) {
        String value = v.getText().toString();
        try {
            return Float.parseFloat(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 返回扣除社保医保公积金后薪水
     * @param salaryBeforeTax 扣除社保医保公积金前薪水
     * @return
     */
    public float salaryAfterTax(float salaryBeforeTax)
    {
//      （3W-3.5K）*25%-1005
//      扣税公式是：
//      （扣除社保医保公积金后薪水-个税起征点）*税率-速算扣除数
        float taxbase=salaryBeforeTax-3500;
        int Taxrate=0;//这里税率没有除以百分比；
        int Quickdeduction=0;
        if(taxbase <=0)//低于个税起征点
        {
            return salaryBeforeTax;
        }else if(taxbase <=1500)
        {
            Taxrate=3;
            Quickdeduction=0;
        }else if(taxbase <=4500)
        {
            Taxrate=10;
            Quickdeduction=105;
        }else if(taxbase <=9000)
        {
            Taxrate=20;
            Quickdeduction=555;
        }else if(taxbase <=35000)
        {
            Taxrate=25;
            Quickdeduction=1005;
        }else if(taxbase <=55000)
        {
            Taxrate=30;
            Quickdeduction=2755;
        }else if(taxbase <=80000)
        {
            Taxrate=35;
            Quickdeduction=5505;
        }else
        {
            Taxrate=45;
            Quickdeduction=13505;
        }
        return salaryBeforeTax-((salaryBeforeTax-3500)*Taxrate/100-Quickdeduction);
    }

    public static float getYearAfterTax(float year) {
        float yearPerMonth = year /12;
        float taxbase = yearPerMonth;
//        if(yearPerMonth )
        int Taxrate=0;//这里税率没有除以百分比；
        int Quickdeduction=0;

        if(taxbase <=1500)
        {
            Taxrate=3;
            Quickdeduction=0;
        }else if(taxbase <=4500)
        {
            Taxrate=10;
            Quickdeduction=105;
        }else if(taxbase <=9000)
        {
            Taxrate=20;
            Quickdeduction=555;
        }else if(taxbase <=35000)
        {
            Taxrate=25;
            Quickdeduction=1005;
        }else if(taxbase <=55000)
        {
            Taxrate=30;
            Quickdeduction=2755;
        }else if(taxbase <=80000)
        {
            Taxrate=35;
            Quickdeduction=5505;
        }else
        {
            Taxrate=45;
            Quickdeduction=13505;
        }

        return year - (year*Taxrate - Quickdeduction);
    }


}
