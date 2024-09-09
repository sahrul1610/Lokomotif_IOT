import KafkaConfig from '../configs/config.js';

// Controller untuk mengirim pesan ke Kafka
const sendMessageToKafka = async (req, res) => {
    try {
        const messages = req.body;              // Mendapatkan pesan dari body permintaan
        const kafkaConfig = new KafkaConfig();  // Membuat instance dari KafkaConfig

        await kafkaConfig.produce('lokomotif-topic', messages); // Mengirim pesan ke Kafka

        res.status(200).json({
            status: 'OK!',
            message: 'Message successfully sent to Kafka!', // Mengirim respons sukses
        });
    } catch (err) {
        console.log(err);       // Mencatat kesalahan apapun
        res.status(500).json({
            status: 'Error',
            message: 'Failed to send the message to Kafka',  // Mengirim respons kesalahan
        });
    }
};

const controllers = { sendMessageToKafka };

export default controllers;
