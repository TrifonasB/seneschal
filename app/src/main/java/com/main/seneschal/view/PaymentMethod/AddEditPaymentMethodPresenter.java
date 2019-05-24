package com.main.seneschal.view.PaymentMethod;

import com.main.seneschal.dao.PaymentMethodDAO;
import com.main.seneschal.domain.Card;
import com.main.seneschal.domain.CardType;
import com.main.seneschal.domain.PaymentMethod;
import com.main.seneschal.util.Money;
import com.main.seneschal.util.SimpleCalendar;
import com.main.seneschal.util.SystemDate;

import java.math.BigDecimal;
import java.util.List;

public class AddEditPaymentMethodPresenter {

    AddEditPaymentMethodView view;
    PaymentMethodDAO paymentMethods;
    PaymentMethod attachedPaymentMethod;

    public AddEditPaymentMethodPresenter(AddEditPaymentMethodView view, PaymentMethodDAO paymentMethods) {
        this.view = view;
        this.paymentMethods = paymentMethods;

        Integer attachedPaymentMethodID = view.getAttachedPaymentMethodID();
        attachedPaymentMethod = attachedPaymentMethodID == null ? null : paymentMethods.find(attachedPaymentMethodID);

        if(attachedPaymentMethod!=null){
            view.setBalance(attachedPaymentMethod.getBalance().getAmount().intValue());
            if(attachedPaymentMethod instanceof Card) {
                view.setPaymentMethodType("Card");
                view.setCardNo(((Card) attachedPaymentMethod).getCardNo());
                view.setExpirationDate(((Card) attachedPaymentMethod).getExpires());
                view.setCardType(((Card) attachedPaymentMethod).getCardType());
            }else{
                view.setPaymentMethodType("Wallet");
            }
        }
    }

    public void onSaveCard() {
        Money balance = Money.euros(view.getBalance());
        String cardNo = view.getCardNo();
        SimpleCalendar expires = view.getExpirationDate();
        CardType cardType = view.getCardType();

        if (cardNo.length() != 16) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε ακριβώς 16 αριθμητικά ψηφία για τον αριθμό της κάρτας.");
        }else if (expires.before(SystemDate.now())){
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε μια μελλοντική ημερομηνία λήξης ώστε η κάρτα να είναι έγκυρη.");
        }else if (balance.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε ένα θετικό υπόλοιπο ώστε η κάρτα να είναι έγκυρη.");
        }else{
            if(attachedPaymentMethod == null){
                Card card = new Card(cardNo,expires,cardType,balance);
                paymentMethods.save(card);

                view.successfullyFinishActivity("Επιτυχής προσθήκη της κάρτας με αριθμό " + cardNo + "!");
            }else{
                attachedPaymentMethod.setBalance(balance);
                ((Card)attachedPaymentMethod).setExpires(expires);

                view.successfullyFinishActivity("Επιτυχής τροποποίηση της κάρτας με αριθμό " + cardNo + "!");
            }
        }

    }

    public void onDeleteCard(){
        String cardNo = view.getCardNo();

        paymentMethods.delete(attachedPaymentMethod);

        view.successfullyFinishActivity("Επιτυχής διαγραφή της κάρτας με αριθμό " + cardNo + "!");
    }

    public void onUpdateWalletBalance(){
        Money balance = Money.euros(view.getBalance());

        if (balance.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            view.showErrorMessage("Σφάλμα!", "Συμπληρώστε ένα θετικό υπόλοιπο για το πορτοφόλι.");
        }else{
            attachedPaymentMethod.setBalance(balance);
            view.successfullyFinishActivity("Επιτυχής ενημέρωση του πορτοφολιού.");
        }
    }

    public List<PaymentMethod> getPaymentMethods(){
        return paymentMethods.findAll();
    }
}
