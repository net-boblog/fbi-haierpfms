package pfms.repository.model.custom;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * ����������
 */
@XStreamAlias("pifcs")
public class ReqGuojie {
    @XStreamAlias("Head")
    private ReqHeader reqHeader; // ������ͷ

    @XStreamAlias("Body")
    private ReqBody reqBody = new ReqBody(); // ��������

    public ReqHeader getReqHeader() {
        return reqHeader;
    }

    public void setReqHeader(ReqHeader reqHeader) {
        this.reqHeader = reqHeader;
    }

    public ReqBody getReqBody() {
        return reqBody;
    }

    public void setReqBody(ReqBody reqBody) {
        this.reqBody = reqBody;
    }

    public static class ReqHeader {
        private String tx_code = "83330001";  // ������
        private String branch_no = "010";     // ������
        private String teller_no = "0000060"; // ��Ա��
        private String channel_flag = "04";   // ������ʶ
        private String request_date;          // ��������
        private String request_time;          // ����ʱ��
        private String request_seq_no;        // ������ˮ��
        private String terminal_info;         // �ͻ����������ն�
        private String pin_node;              // ���ܽڵ��

        public String getTx_code() {
            return tx_code;
        }

        public void setTx_code(String tx_code) {
            this.tx_code = tx_code;
        }

        public String getBranch_no() {
            return branch_no;
        }

        public void setBranch_no(String branch_no) {
            this.branch_no = branch_no;
        }

        public String getTeller_no() {
            return teller_no;
        }

        public void setTeller_no(String teller_no) {
            this.teller_no = teller_no;
        }

        public String getChannel_flag() {
            return channel_flag;
        }

        public void setChannel_flag(String channel_flag) {
            this.channel_flag = channel_flag;
        }

        public String getRequest_date() {
            return request_date;
        }

        public void setRequest_date(String request_date) {
            this.request_date = request_date;
        }

        public String getRequest_time() {
            return request_time;
        }

        public void setRequest_time(String request_time) {
            this.request_time = request_time;
        }

        public String getRequest_seq_no() {
            return request_seq_no;
        }

        public void setRequest_seq_no(String request_seq_no) {
            this.request_seq_no = request_seq_no;
        }

        public String getTerminal_info() {
            return terminal_info;
        }

        public void setTerminal_info(String terminal_info) {
            this.terminal_info = terminal_info;
        }

        public String getPin_node() {
            return pin_node;
        }

        public void setPin_node(String pin_node) {
            this.pin_node = pin_node;
        }
    }

    public static class ReqBody {
        private String trade_date; // ��������

        public String getTrade_date() {
            return trade_date;
        }

        public void setTrade_date(String trade_date) {
            this.trade_date = trade_date;
        }
    }
}
