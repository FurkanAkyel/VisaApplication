package com.furkanakyel.visaapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FoodListActivity : AppCompatActivity() {

    private val foodList = mutableListOf<Food>()
    private lateinit var foodAdapter: FoodAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_list)

        foodList.add(
            Food(
                "Kavurmalı Yumurtalı Otlu Peynir",
                "Malzemeler:\n- 200 gram kavurma\n- 2 adet yumurta\n- 100 gram otlu peynir\n- 1 yemek kaşığı tereyağı\n- Tuz ve karabiber",
                "1. Kavurmayı tavada ısıtarak pişirin. Kavurma daha önce hazırlanmışsa, tavada sadece ısıtın.\n" +
                        "2. Yumurtaları tavaya kırın, beyazları pişene kadar karıştırmadan pişirin. Sarıları isteğinize göre pişirebilirsiniz.\n" +
                        "3. Yumurtalar piştikten sonra, otlu peyniri serpin. Eğer otlu peynir yoksa, beyaz peynir de kullanılabilir.\n" +
                        "4. Tuz ve karabiber ile tatlandırın. Sıcak servis edin, yanına taze ekmekle sunabilirsiniz."
            )
        )
        foodList.add(
            Food(
                "Van Balığı Izgara",
                "Malzemeler:\n- 2 adet inci kefali\n- 3 yemek kaşığı zeytinyağı\n- 1 tatlı kaşığı tuz\n- 1 çay kaşığı karabiber\n- 1 limon suyu\n- Kekik ve pul biber",
                "1. İnci kefalini temizleyin ve üzerine tuz ile karabiber serpin.\n" +
                        "2. Zeytinyağı, limon suyu ve kekik ekleyerek 15-20 dakika marine edin.\n" +
                        "3. Izgarayı ısıtın ve balıkları yerleştirip her iki tarafını 5-7 dakika pişirin.\n" +
                        "4. Izgara balığı, roka ve garnitürle servis yapın. Yanında taze ekmekle servis edebilirsiniz."
            )
        )
        foodList.add(
            Food(
                "Keledoş",
                "Malzemeler:\n- 500 gram et (tercihen dana eti)\n- 1 su bardağı mercimek\n- 1 su bardağı buğday\n- 1/2 su bardağı nohut\n- 1 su bardağı yoğurt\n- 2 yemek kaşığı tereyağı\n- Tuz, karabiber, pul biber",
                "1. Mercimek, nohut ve buğdayı ayrı ayrı haşlayın. Nohut ve mercimek biraz daha uzun sürede pişer, önceden hazırlayın.\n" +
                        "2. Eti küçük parçalar halinde doğrayın ve tereyağında kavurun.\n" +
                        "3. Haşlanmış malzemeleri geniş bir tencereye alın, etleri de ekleyin ve kısık ateşte pişirmeye devam edin.\n" +
                        "4. Yoğurdu çırpın ve tencereye ekleyin. Karıştırarak pişirin.\n" +
                        "5. Baharatları ekleyin ve tatlandırın.\n" +
                        "6. Sıcak servis edin ve yanında taze ekmek ile sunabilirsiniz."
            )
        )
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        foodAdapter = FoodAdapter(foodList) { selectedFood ->
            val intent = FoodDetailActivity.newIntent(this, selectedFood)
            startActivity(intent)
        }
        recyclerView.adapter = foodAdapter

        foodAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_food -> {
                showAddFoodDialog()
                true
            }

            R.id.action_game -> {
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun showAddFoodDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_food, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Yeni Yemek Ekle")
            .setView(dialogView)
            .setPositiveButton("Ekle") { _, _ ->
                val foodName =
                    dialogView.findViewById<android.widget.EditText>(R.id.etFoodName).text.toString()
                val foodIngredients =
                    dialogView.findViewById<android.widget.EditText>(R.id.etIngredients).text.toString()
                val foodRecipe =
                    dialogView.findViewById<android.widget.EditText>(R.id.etRecipe).text.toString()

                if (foodName.isNotEmpty() && foodIngredients.isNotEmpty() && foodRecipe.isNotEmpty()) {
                    // Yeni yemek ekle
                    foodList.add(Food(foodName, foodIngredients, foodRecipe))
                    foodAdapter.notifyItemInserted(foodList.size - 1)
                    Toast.makeText(this, "$foodName eklendi!", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("İptal", null)
            .create()
        dialog.show()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Uyarı")
            .setMessage("Giriş ekranına dönmek istediğinize emin misiniz?")
            .setPositiveButton("Evet") { _, _ ->
                val intent = Intent(this, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            }
            .setNegativeButton("Hayır") { _, _ ->
            }
            .create()
            .show()
    }
}
