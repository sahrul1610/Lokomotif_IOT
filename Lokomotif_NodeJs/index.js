import express from 'express';
import bodyParser from 'body-parser';
import controllers from './controllers/controller.js';
import KafkaConfig from './configs/config.js';

const app = express();
const port = 3000;

app.use(bodyParser.json()); // Menggunakan body parser untuk mengurai JSON

// Endpoint untuk mengirim data ke Kafka
app.post('/api/send', controllers.sendMessageToKafka);

// Mengkonsumsi pesan dari Kafka dan menyimpannya ke MongoDB
const kafkaConfig = new KafkaConfig();
kafkaConfig.consume('lokomotif-topic', (value) => {
    console.log('Consumed message from Kafka:', value);
});

// Menjalankan server
app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
});
