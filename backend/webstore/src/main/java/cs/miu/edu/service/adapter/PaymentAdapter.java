package cs.miu.edu.service.adapter;

import cs.miu.edu.domain.Payment;
import cs.miu.edu.service.dto.PaymentDTO;

/**
 * @author Prabhat Gyawali
 * @created 12-Jul-2021 - 10:26 PM
 * @project webstore
 */
public class PaymentAdapter {

    public static Payment getPayment(PaymentDTO paymentDTO){
        Payment payment = new Payment(paymentDTO.getCreditCardType(),
                paymentDTO.getNumber(),
                paymentDTO.getValidDate(),
                paymentDTO.getValidationCode());
        return payment;
    }
    public static PaymentDTO getPaymentDTO(Payment payment){
        PaymentDTO paymentDTO = new PaymentDTO(payment.getCreditCardType(),
                payment.getNumber(),
                payment.getValidDate(),
                payment.getValidationCode());
        return paymentDTO;
    }



}
