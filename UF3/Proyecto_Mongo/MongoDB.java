package Proyecto_Mongo;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

public class MongoDB {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static boolean executed = true;


    public static void main(String[] args) throws IOException {
        MongoClient  client = new MongoClient(); 
        MongoDatabase dataBase = client.getDatabase ("clothes"); 
        MongoCollection<org.bson.Document> collection = dataBase.getCollection("fashionnova");

        do {
            optionMenu(printMenu(), collection);            
        } while (executed == true);
    }
    
    
    public static String printMenu() throws IOException {
    	System.out.println("********************************************");
        System.out.println("            This is FASHION NOVA            ");
        System.out.println("********************************************");
        System.out.println("      What you want to do?           ");
        System.out.println("");
        System.out.println("A. Insert clothes in Data Base: ");
        System.out.println("B. Show full Data Base: ");
        System.out.println("C. Show Data Base ordered by cloth type: ");
        System.out.println("D. Modify cloth characteristics: ");
        System.out.println("E. Delete cloth piece: ");
        System.out.println("F. Exportar Data Base into text file: ");
        System.out.println("G. Exit: ");
        System.out.println("");
        String user = reader.readLine();
        return user;
    }
    
    public static void optionMenu(String userInput, MongoCollection<org.bson.Document> collection) {
    	switch(userInput.toUpperCase()) {            
        case "A":
            insertClothesIntoDatabase(collection);
            break;
        case "B":
            printDatabase(collection);
            break;
        case "C":
            printDatabaseByClothType(collection);
            break;
        case "D":
            renameProduct(collection);
            break;
        case "E":
            removeCloth(collection);
            break;
        case "F":
            exportToTextfile(collection);
            break;
        case "G":
            closeDatabase();
            break;
        default:
            System.out.print("We can't read this option. Choose a valid one.");
            break;
        }
    }
    
    public static void closeDatabase() {
    	System.out.println("See you soon!!");
    	executed = false;
    }
    
    public static void insertClothesIntoDatabase(MongoCollection<org.bson.Document> collection) throws IOException {
        Document outfit = new Document();
        
        // Insertamos los primeros datos de la pieza de ropa (Nombre, descripcion, talla, referencia)
        System.out.print("Name of the cloth: ");
        String name = reader.readLine();
        outfit.put("name",name); 
        
        System.out.print("Product description: ");
        String description = reader.readLine();
        boolean lettersValidation = description.chars().allMatch(Character::isLetter);

        while (lettersValidation == false) {
            System.out.println("Description can't contain numbers, it's a definition of the outfit.");
            description = reader.readLine();
            lettersValidation = description.chars().allMatch(Character::isLetter);
        }
        outfit.put("description", description); 
                
        System.out.print("Ref. number: ");
        String refNumber = reader.readLine();
        
        while (refNumber.length() != 8) {
            System.out.println("Vuelve a introducir el número de serie. Este número debe tener 8 caracteres: ");
            refNumber = reader.readLine();
        }
        outfit.put("numero", refNumber);
        
        System.out.print("Cuantas piezas hay en stock: ");
        String stock = reader.readLine();
        boolean allNumbers = description.chars().allMatch(Character::isDigit);
        
        while (allNumbers == false) {
            System.out.println("La cantidad de producto debe ser un numero entero. Vuelve a introducir la cantidad: ");
            stock = reader.readLine();
            allNumbers = stock.chars().allMatch(Character::isLetter);
        }
        
        int finalStock = Integer.parseInt(stock);
        outfit.put("stock", finalStock); 
        
        System.out.print("Size: ");
        String[] size = {"XS", "S", "M", "L", "XL", "XXL"};
        String userSize = reader.readLine();
        
        boolean isCorrect = false;
        while (isCorrect == false) {
            for (int i=0; i < size.length; i++) {
                if (userSize.equals(size[i])) {
                    isCorrect = true;
                }
            }

            if (isCorrect) {
                outfit.put("size", userSize);
            } else {
                System.out.println("Clothes size can't be a number. Set a valid input: "+ size +": ");
                userSize = reader.readLine();
            }
        }    
        collection.insertOne(outfit);
    }
    
    public static void printDatabase(MongoCollection<org.bson.Document> collection) {
        List<Document> query = collection.find().into(new ArrayList<Document> ()); 
        
        for (int i =0; i < query.size(); i++) { 
            System.out.println(i+". " +  query.get(i).toString()); 
        }
    }

    public static void printDatabaseByClothType(MongoCollection<org.bson.Document> collection) throws IOException {
        MongoCursor<Document> cursor = collection.find().iterator(); 
        System.out.println("Que tipo de piezas deseas visualizar: ");
        String type = reader.readLine();
        
        while (cursor.hasNext()) {
            Document document = cursor.next(); 
            if(document.getString("tipo").equals(type)) {
                System.out.println (document.toJson().toString()); 
            }
        }
        cursor.close();
    }

    public static void renameProduct(MongoCollection<org.bson.Document> collection) throws IOException {
        System.out.println("Escribe el nombre del producto que deseas modificar: ");
        String oldName = reader.readLine();    
        Document oldData = new Document().append("nombre", oldName);
        
        Document newData = new Document();
        System.out.println("Escribe el nuevo nuevo: ");
        String newName = reader.readLine();
        newData.append("$set", new Document().append("nombre", newName));

        collection.updateOne(oldData, newData);
    }

    public static void removeCloth(MongoCollection<org.bson.Document> coleccion) throws IOException {
        System.out.println("Escribe el numero de serie del producto que deseas eliminar: ");
        String serialNumber = reader.readLine();
        coleccion.deleteOne(new Document("numero", serialNumber));
    }

    public static void exportToTextfile(MongoCollection<org.bson.Document> collection) {
        MongoCursor<Document> cursor = collection.find().iterator(); 
        FileWriter file = null;
        PrintWriter writer = null;
        
        try{
            file = new FileWriter("C:\\Users\\Jessica\\Desktop\\Mongodb\\fashionnova.txt");
            writer = new PrintWriter(file);
            System.out.println("Escribe el tipo de piezas que deseas exportar: ");
            String type = reader.readLine();
            while (cursor.hasNext()) {
                Document document = cursor.next(); 
                if(document.getString("tipo").equals(type)) {
                    writer.print(document.toJson()); 
                }
            }
            cursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != file)
                    file.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }
}