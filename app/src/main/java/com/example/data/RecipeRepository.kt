package com.example.data

object RecipeRepository {

    const val LOGO_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuCvg0kZzux7b0CVqfvMO6Ydk0T3X3MyDcuEZ6nmUoTPqsuoh79l6zdCbEU9BJn2wLJsrVjJZ7GbSmltSm-4147DGhQluNp3fnnZ5OfRnHDAjXkRYIUbXCfcyvBj6Y_7i3TR5bKSkdcXc2JbM0mes9yWL2wU52UaL5dtM5GjDINI8eEv__AXBN4DV8eEYvGHFPy1j-nwFvA57bcGbdfv8JC_8UmkMhGct1gyb3UFBltNmB-SBIJlX-uW6Q"
    const val CHEF_AVATAR_URL = "https://lh3.googleusercontent.com/aida-public/AB6AXuB7iIbpVmbgNbECxlhr1g2V7rMc03uRedXgufyPo9imV-_0KzrIlUF9Zs5gR0V1HVg-eglbmyNdFO1aOCjDdT_NtJAQu1tqHtQlh__hXcCbaqGka5VkwWdgNtqb7MRICBFBps9MQ7SXFvo4JLUMb_1jYfk9aIGniw37Fu3TgZctdfAnybdkqTru5V9JuvIguPvio6DUaIDf2brTabBMGjzzW37C82H90L7T3UXQg5CyrB97tbJ9MZL7RA"

    val featuredMaasorTenga = Recipe(
        id = "maasor-tenga",
        title = "Traditional Maasor Tenga (Rohu Fish with Ou Tenga)",
        assameseTitle = "মাছৰ টেঙা • Maasor Tenga",
        subtitle = "River Fish in Wild Elephant Apple & Heirloom Tomato Broth",
        description = "Light, restorative Brahmaputra sweet-water fish simmered in sun-dried elephant apple (Ou Tenga) broth, finished with freshly crushed ginger and aromatic Kaji Nemu lime juice.",
        imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBUpn7PzTfeusC9S2W1JuoLvZpXJisjLmzhQKAxyHZS_QuihKh-qez4cTN3ldcE0LdR_Mpo7K5zI78bo6kZEONkbpD7VpNC3S1vAbaspfxfvB4iKkNvtochZ3GHotQXIHJVeN0_ESNAIeftgW1oKAU_u7nYZM5wFW5skihXsmQfHy5D0PzyhnVYWq-F1f-tiIZVd5m1CxX-GSAnjYzMdThgdc-0SEXr_1v57KUgzmlMBdOwKXjI-w4KBQ",
        category = "tenga",
        categoryLabel = "Tenga (Tangy)",
        tags = listOf("Tangy & Refreshing", "Heritage Broth"),
        prepTimeMins = 15,
        cookTimeMins = 25,
        energyKcal = 285,
        tasteProfile = "Mild-Sour",
        level = "Medium",
        rating = 4.9,
        reviewCount = 184,
        region = "Summer Heritage Thali",
        culturalNote = "“A cornerstone of our summer thali. The sourness must come naturally from elephant apple (Ou Tenga) and freshly squeezed Assam Kaji Nemu, tempered gently in pungent raw mustard oil. We never disguise the river fish; we honor it.”",
        chefNote = "Always heat mustard oil until faint smoke appears before tempering.",
        baseServings = 4,
        ingredients = listOf(
            Ingredient("1", "Fresh Rohu/Katla fish steaks", "নদীৰ ৰৌ মাছ", 500.0, "g"),
            Ingredient("2", "Elephant Apple (Ou Tenga), sliced thin", "ঔ টেঙা", 0.5, "whole"),
            Ingredient("3", "Cold-pressed Mustard Oil", "মিঠা তেল", 2.0, "tbsp"),
            Ingredient("4", "Paanch Phoron (Assam five-spice blend)", null, 1.0, "tsp"),
            Ingredient("5", "Green chilies, slit lengthwise", null, 2.0, "pcs"),
            Ingredient("6", "Turmeric powder & rock salt", null, 0.5, "tsp"),
            Ingredient("7", "Assam Kaji Nemu wedge (for finish)", null, 1.0, "wedge"),
            Ingredient("8", "Fresh coriander sprigs", "ধনিয়া", 1.0, "Handful")
        ),
        steps = listOf(
            CookingStep(
                stepNumber = 1,
                title = "The Golden Sear",
                instruction = "Rub fish steaks with turmeric and salt. Heat raw mustard oil in an iron kadai until lightly smoking. Gentle shallow fry the seasoned fish steaks for exactly 2 minutes on each side until golden crisp. Transfer gently and reserve.",
                timerSeconds = 120,
                timerLabel = "Fish Sear Timer"
            ),
            CookingStep(
                stepNumber = 2,
                title = "Aromatic Tempering",
                instruction = "In the fragrant residual mustard oil, release paanch phoron and crushed slit green chilies. Once crackling, introduce thinly cut Ou Tenga (elephant apple) wedges. Lightly sauté for 3 minutes until slightly softened."
            ),
            CookingStep(
                stepNumber = 3,
                title = "Awakening the Broth",
                instruction = "Pour 2.5 cups of warm water over the tempered spices. Bring to a vigorous rolling boil to coax out the natural pectin and sharp, sour tang from the Ou Tenga pulp into the pale golden broth."
            ),
            CookingStep(
                stepNumber = 4,
                title = "Simmer & Kaji Nemu Finish",
                instruction = "Slide in the fried fish steaks. Lower the flame and simmer uncovered for 8 minutes so the fish absorbs the tartness. Turn off the heat; immediately squeeze fresh Assam Kaji Nemu juice and scatter freshly plucked coriander sprigs.",
                timerSeconds = 480,
                timerLabel = "Simmer Broth Timer"
            )
        ),
        pairingTip = "Best savored poured over steaming Joha Saul (fragrant indigenous sticky rice) accompanied by raw shallots and fresh green chilli.",
        isFeatured = true
    )

    val trendingRecipes = listOf(
        Recipe(
            id = "omita-khar",
            title = "Omita Khar (Raw Papaya Stew)",
            assameseTitle = "অমিতা খাৰ • Omita Khar",
            subtitle = "Starter Healer",
            description = "Raw green papaya tempered with ginger and indigenous banana peel ash water (Kolakhar).",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDK8_CImlPhXrU0G0HvWDTIc1LhwG0AAOpxa0WUQM8XEWeDIFktPc3QIRLUS9U7dx7XatLWalS1QBFByCQw2jKMeNmtFWL8svVNJbk5o7VjcQoL33RosB2_ELTZPJn1GxzoOOZdvWDGc2fqgG1yfBhs4pXrGNa1ITUMiulsBp1Vj1KGJ3sLatTsfrBN9jYdZJR-PAsQ0XtvsPrxJuQFJCZqO0wpZt-2DyqoZidowyXvaTSLwf8-TRIy7g",
            category = "khaar",
            categoryLabel = "Khaar (Alkaline)",
            tags = listOf("Alkaline", "Starter Healer"),
            prepTimeMins = 10,
            cookTimeMins = 25,
            energyKcal = 120,
            tasteProfile = "Alkaline Cleanse",
            level = "Easy",
            rating = 4.8,
            reviewCount = 92,
            region = "Central Assam",
            culturalNote = "Khar always begins a traditional Assamese meal. The mild alkaline ash cleanses the palate and facilitates healthy gut digestion.",
            ingredients = listOf(
                Ingredient("ok1", "Raw green papaya, peeled and cubed", "কেঁচা অমিতা", 350.0, "g"),
                Ingredient("ok2", "Kola Khar extract (banana peel ash liquid)", "কলা খাৰ", 2.0, "tbsp"),
                Ingredient("ok3", "Mustard oil", "মিঠা তেল", 1.5, "tbsp"),
                Ingredient("ok4", "Garlic cloves, crushed", null, 6.0, "pcs"),
                Ingredient("ok5", "Green chilies", null, 2.0, "pcs"),
                Ingredient("ok6", "Panch phoron", null, 0.5, "tsp")
            ),
            steps = listOf(
                CookingStep(1, "Tempering", "Heat mustard oil in a heavy pot until fragrant. Add crushed garlic and green chillies until lightly browned."),
                CookingStep(2, "Stewing", "Toss in the diced papaya with salt. Stir well for 3 minutes before pouring in 2 cups of hot water."),
                CookingStep(3, "Adding Khar", "When the papaya is tender, stir in the filtered Kolakhar extract. Simmer for 5 minutes until soft and creamy.")
            ),
            pairingTip = "Serve warm with steaming white rice as the opening course of the lunch thali."
        ),
        Recipe(
            id = "haah-lahi-xaak",
            title = "Haah Lahi Xaak (Duck & Greens)",
            assameseTitle = "হাঁহ আৰু লাহি শাক • Haah Xaak",
            subtitle = "Heritage Game",
            description = "Slow braised country duck meat infused with hand-plucked river valley wild water greens.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDzKse53Wy9G85YyboZkTgP61bfFbrsEbwUXUqZnkLYTOqH9ox5n9__08idoWlRX1ehL-bRLmRMIxh4XFTrawxqfLtPDgeXFO5YKp7LXs-ckBlREg2dcwq428uw_xnekRhSt2TMVJxX1lPWj5wudP16qh6SOWWWFIxSOEYQkg_PT_wkV0IoAhPj3bOHIQ1ETHrpMuM6hz7igKf1zAeIQ9AJcX7qSX2CQYiNh47Shn7xOqy_H5GHBML6Pg",
            category = "poultry",
            categoryLabel = "Duck & Pigeon",
            tags = listOf("Rich Feast", "Heritage Game"),
            prepTimeMins = 20,
            cookTimeMins = 55,
            energyKcal = 420,
            tasteProfile = "Spicy Warmth",
            level = "Medium",
            rating = 5.0,
            reviewCount = 118,
            region = "Lower Assam Rivers",
            culturalNote = "A celebratory Magh Bihu dish cooked outdoors on firewood hearths with wild greens and black pepper.",
            ingredients = listOf(
                Ingredient("hl1", "Country duck, bone-in pieces", "পাতি হাঁহৰ মাংস", 750.0, "g"),
                Ingredient("hl2", "Lahi Xaak (tender mustard greens)", "লাহি শাক", 200.0, "g"),
                Ingredient("hl3", "Whole black peppercorns, crushed", null, 1.5, "tsp"),
                Ingredient("hl4", "Ginger garlic paste", null, 2.0, "tbsp"),
                Ingredient("hl5", "Mustard oil", null, 3.0, "tbsp")
            ),
            steps = listOf(
                CookingStep(1, "Braising", "Brown duck pieces in smoking mustard oil until rendered and deep golden.", timerSeconds = 600, timerLabel = "Duck Browning Timer"),
                CookingStep(2, "Spice Infusion", "Add ginger, garlic, ground pepper, and turmeric. Cook on gentle heat until aromatic oil separates."),
                CookingStep(3, "Simmer with Greens", "Add chopped wild greens and water. Cover and slow cook until the duck is meltingly tender.", timerSeconds = 1800, timerLabel = "Slow Simmer Timer")
            ),
            pairingTip = "Pairs magnificently with Joha rice and roasted green chilies."
        ),
        Recipe(
            id = "alu-pitika",
            title = "Alu Pitika (Mustard Mash)",
            assameseTitle = "আলু পিটিকা • Alu Pitika",
            subtitle = "Everyday Soul",
            description = "Hand-mashed roasted potatoes whipped with pungent raw mustard oil, chopped shallots, and fiery chilies.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAMqBq7OvSItqxn7rJpep_cG_PsywA2-8HlUH5zjyHZ7x3YUwrhdsj0qYK8dI2NXIt3Rf_JqRvFtEGc72mN-xvfhdgkoAorp2ysCfsmOfyCjUXF8xoj4iEa2xUYyhEmXoe8O_h2__EyR-B85ruwlrxH4HO3eWiVTo8MXAvT20EQqy7vuuwu1Lm7ylkoh79XmTMP1sJH6iQzMOUeoECzAC9daJIh68JIRUPYLVlnlXh1BYxmw5NJvXnALQ",
            category = "pitika",
            categoryLabel = "Pitika (Mashes)",
            tags = listOf("Pungent Comfort", "Everyday Soul"),
            prepTimeMins = 5,
            cookTimeMins = 15,
            energyKcal = 160,
            tasteProfile = "Pungent Comfort",
            level = "Easy",
            rating = 4.9,
            reviewCount = 205,
            region = "All Assam",
            culturalNote = "The undisputed soul comfort of every Assamese home. The raw mustard oil is never heated, retaining its natural pungent kick.",
            ingredients = listOf(
                Ingredient("ap1", "Boiled or ember-roasted potatoes", "আলু", 4.0, "large"),
                Ingredient("ap2", "Raw cold-pressed mustard oil", "কেঁচা মিঠা তেল", 1.5, "tbsp"),
                Ingredient("ap3", "Shallots or red onions, finely chopped", "পিয়াঁজ", 1.0, "medium"),
                Ingredient("ap4", "Fresh green bird's eye chilies", "কেঁচা জলকীয়া", 2.0, "pcs"),
                Ingredient("ap5", "Coriander leaves and rock salt", null, 1.0, "Handful")
            ),
            steps = listOf(
                CookingStep(1, "Mashing", "Peel potatoes while still hot. Mash thoroughly with your fingers until smooth with slight rustic texture."),
                CookingStep(2, "Emulsifying", "Add chopped shallots, green chilies, and salt. Pour unheated raw mustard oil directly over the mash and whip until fragrant.")
            ),
            pairingTip = "Serve alongside yellow masoor dal and hot rice."
        ),
        Recipe(
            id = "til-pitha",
            title = "Til Pitha (Sesame & Jaggery)",
            assameseTitle = "তিল পিঠা • Til Pitha",
            subtitle = "Heritage Dessert",
            description = "Crisp cylindrical rolled glutinous rice crepes filled with toasted black sesame and dark sticky jaggery.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCtVq6mfoK5BSzTNqTULCR7BaZg7G6ey58POuPlRe6ckYCamtF9RpadInZzG9ctvNj7Wk7qhw-3LF6yrAR37aPFt4J7raHC-r-xY9uMkwx8tAUsSQuXsEDy70x1847brLhChOZzsJoWn6aDRHvWgxvmpyeJDfz5N8CxJxrIwYxLTKm6KfnsWlDJGSmMwvOgRhGrrIRSyWhsJKk3_21A2yT7xnMTw-cFaaCezK7RzrS9isrfoswupXmK8A",
            category = "sweets",
            categoryLabel = "Bihu Pitha",
            tags = listOf("Bihu Sweet", "Heritage Dessert"),
            prepTimeMins = 15,
            cookTimeMins = 30,
            energyKcal = 210,
            tasteProfile = "Festive Sweet",
            level = "Medium",
            rating = 4.9,
            reviewCount = 144,
            region = "Upper Assam",
            culturalNote = "Crafted during Magh Bihu from soaked and hand-pounded Bora Saul rice flour rolled on a dry cast iron tawa without oil.",
            ingredients = listOf(
                Ingredient("tp1", "Sticky Bora rice flour, sieved", "বৰা চাউলৰ গুড়ি", 250.0, "g"),
                Ingredient("tp2", "Black sesame seeds, roasted", "কলা তিল", 100.0, "g"),
                Ingredient("tp3", "Liquid date palm or sugarcane jaggery", "গুড়", 120.0, "g")
            ),
            steps = listOf(
                CookingStep(1, "Filling Prep", "Gently toast black sesame seeds until popping. Mix with molten jaggery to form a sticky fragrant filling."),
                CookingStep(2, "Griddling", "Spread a thin circular layer of moist rice flour on a hot dry skillet without pressing."),
                CookingStep(3, "Rolling", "Place a spoon of sesame filling in the center. As the rice flour binds, gently roll into a firm cylinder.")
            ),
            pairingTip = "Enjoy warm with a cup of strong Assam Lal Cha (black tea)."
        )
    )

    val exploreCatalog = listOf(
        Recipe(
            id = "chitol-machor-jhol",
            title = "Chitol Machor Jhol",
            assameseTitle = "চিতল মাছৰ ঝোল • Chitol Machor Jhol",
            subtitle = "Upper Assam Heritage",
            description = "Prized Brahmaputra clown knifefish gently poached in a restorative ginger, cumin, and fresh turmeric broth.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBnM_xdcftF5DXYnrG7CimEQxkRCbGpu0kjwOg9Ke85eQQrq0qkpQf9NAABCYCZ5K1LvrbrX0nTR4Vr5LQwg4k-T9hr2OlFVX6GZT6MV7LFTND3PyuKSUNJL75_ccC-l-GvJh269G2Mb5RChGoWk9t_gttLVOIJ-d_JiNyWkajytAyPB6IYxr3gRqBMLlNEXhFhwZ8DbRcOIsuE6erfIj_8Y8puoPja8bDPn8Sfc0Xw83l5grE7kYP9Rg",
            category = "fish",
            categoryLabel = "Fish (Maas)",
            tags = listOf("Upper Assam Heritage", "Fish", "Ginger-Infused", "Pure Mustard Oil"),
            prepTimeMins = 15,
            cookTimeMins = 40,
            energyKcal = 310,
            tasteProfile = "Mild Comfort",
            level = "Easy",
            rating = 4.9,
            reviewCount = 142,
            region = "Upper Assam Heritage",
            culturalNote = "Clown knifefish belly (Chitol Peti) contains sweet river fat that melts into the thin, medicinal golden cumin broth.",
            ingredients = listOf(
                Ingredient("cm1", "Chitol fish steaks", "চিতল মাছ", 500.0, "g"),
                Ingredient("cm2", "Fresh ginger paste", "আদা", 1.5, "tbsp"),
                Ingredient("cm3", "Roasted cumin powder", "জীৰা", 1.0, "tsp"),
                Ingredient("cm4", "Mustard oil", "মিঠা তেল", 2.0, "tbsp")
            ),
            steps = listOf(
                CookingStep(1, "Fry Fish", "Lightly sear fish in hot mustard oil for 1.5 minutes per side.", timerSeconds = 90, timerLabel = "Light Sear"),
                CookingStep(2, "Broth Simmer", "Sauté ginger paste, add warm water and cumin, simmer fish until tender.", timerSeconds = 900, timerLabel = "Broth Simmer")
            ),
            pairingTip = "Serve over steaming Aijong or Joha rice."
        ),
        Recipe(
            id = "kharoli-pani-tenga",
            title = "Kharoli & Pani Tenga",
            assameseTitle = "খাৰলি আৰু পানী টেঙা • Kharoli",
            subtitle = "Lower Assam Fermented",
            description = "Aged mustard paste relish made with indigenous khaar and tart sun-dried elephant apple (Ou Tenga) pulp.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAAaYG25MdvYKvU6ZJeNt7EwkkZGTeIAP27tNwJ2cuzWLWl-RBhtvOiTTgHFu2tmNUbFEo4Nb5kEDUa5IyxOa2vzx7Ohe-5xm3GwHcwm03r2-B8fRTFtf-SR1j3XK5t_8I-MO-3xVJqc5cn-EOQSvfOGLx3MrPlNL0gx7SUVAfSvKdnPEqKa-F8mSc24gO_g-c39sPVoEgrDaJOI8S9LU5XBVVs8Q-FR6YBM6UWMT_7hrsWjWfY9K-qNA",
            category = "tenga",
            categoryLabel = "Tenga (Sour)",
            tags = listOf("Lower Assam", "Fermented", "Pungent Zest", "No-Cook"),
            prepTimeMins = 15,
            cookTimeMins = 0,
            energyKcal = 95,
            tasteProfile = "Pungent Zest",
            level = "Traditional",
            rating = 4.8,
            reviewCount = 89,
            region = "Lower Assam",
            culturalNote = "Sun-cured in banana leaf parcels to develop complex probiotic pungency without heating.",
            ingredients = listOf(
                Ingredient("kp1", "Ground mustard paste", "সৰিয়হ বটা", 100.0, "g"),
                Ingredient("kp2", "Kolakhar liquid", "কলা খাৰ", 1.0, "tbsp"),
                Ingredient("kp3", "Sun-dried elephant apple slices", "শুকান ঔ টেঙা", 2.0, "pcs")
            ),
            steps = listOf(
                CookingStep(1, "Blending", "Grind black mustard seeds coarsely with khaar water."),
                CookingStep(2, "Curing", "Shape into balls, roll in banana leaf and allow to ferment in sunlight for 2 days.")
            ),
            pairingTip = "A tiny pinch awakens cold cooked rice or simple dal."
        ),
        Recipe(
            id = "bora-saulor-payas",
            title = "Bora Saulor Payas",
            assameseTitle = "বৰা চাউলৰ পায়স • Bora Payas",
            subtitle = "Festive Bihu Heritage Rice",
            description = "Glutinous red and white Bora rice slow-simmered in buffalo milk with freshly crushed cardamom & Tejpatta.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDMGIKQdd3XGHjeg8MkFBDir8b6OmBoGSE98VzDOp8B8c8i761ZB1TgEfU_G_fMw3vhIhZZWqDyce060-a1A7xeM6NByH68mQNVLiKkw774yJF2lEXAd_Mm3KpXtMHgqT7ks4T05p1rJpAQOtv0ktaWKXyUZRWKgwuq3Mo8hRtVdVwbw0J1_oqvrZ0vlIVxGvjj7WJIWMTxaIIGkAn7ZyjrCTriYSyrnpkOoneAfuaWObqxIS22en-lAw",
            category = "sweets",
            categoryLabel = "Pitha & Sweets",
            tags = listOf("Festive Bihu", "Heritage Rice", "Aromatic", "Natural Jaggery"),
            prepTimeMins = 10,
            cookTimeMins = 30,
            energyKcal = 340,
            tasteProfile = "Sweet",
            level = "Dessert",
            rating = 4.9,
            reviewCount = 210,
            region = "Upper Assam",
            culturalNote = "Bora rice contains high amylopectin, giving this pudding an indulgent silky, naturally thick creaminess.",
            ingredients = listOf(
                Ingredient("bp1", "Sticky Bora rice", "বৰা চাউল", 100.0, "g"),
                Ingredient("bp2", "Whole milk", "গাখীৰ", 1.0, "L"),
                Ingredient("bp3", "Jaggery or unrefined sugar", "গুড়", 150.0, "g"),
                Ingredient("bp4", "Bay leaves (Tejpatta) and cardamom", null, 2.0, "pcs")
            ),
            steps = listOf(
                CookingStep(1, "Simmer Rice", "Cook washed Bora rice in boiling milk on low flame, stirring constantly.", timerSeconds = 1200, timerLabel = "Rice Simmer Timer"),
                CookingStep(2, "Sweeten", "Turn off heat, stir in jaggery syrup and crushed cardamom.")
            ),
            pairingTip = "Serve cool in bell-metal kansa bowls."
        ),
        Recipe(
            id = "koldil-aru-murgi",
            title = "Koldil Aru Murgi",
            assameseTitle = "কলদিল আৰু কুকুৰা মাংস • Koldil Chicken",
            subtitle = "Forest Foraged Slow Braise",
            description = "Finely shredded purple banana flower blossom braised with free-range country chicken and ground whole spices.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCmPpR_9DNMAHgLUVIS7snMXm1MfxTzZvBx1lCSnwS_NzgO1MLh6iB2bC9l0nCN0Y3n3wUBudk2lHP9L_gkE2CnIcKjGG8MB1YZPxdeFgChNDZzvFhcsgErBrBtM5Zr06-GkDpZXAXGX2OIhEDlHd-AGtVurDeVvQps9Ru1dn2JiC4lGMlqU2A_h9la3f1PRD5_OapGpUwC1jzvVScx8nklsFk5JerWl1VaqgGLqqGNaRdBhOSc5JRE-A",
            category = "poultry",
            categoryLabel = "Poultry & Game",
            tags = listOf("Forest Foraged", "Slow Braise", "Iron-Rich", "Nutty Astringency"),
            prepTimeMins = 20,
            cookTimeMins = 45,
            energyKcal = 360,
            tasteProfile = "Mild Comfort",
            level = "Heritage",
            rating = 5.0,
            reviewCount = 95,
            region = "Majuli Island",
            culturalNote = "Banana flower (Koldil) provides cooling astringency and rich iron content that balances game bird richness.",
            ingredients = listOf(
                Ingredient("km1", "Free range chicken pieces", "গাৱঁলীয়া কুকুৰা", 600.0, "g"),
                Ingredient("km2", "Finely shredded banana blossom (Koldil)", "কলদিল", 1.0, "flower"),
                Ingredient("km3", "Mustard oil", "মিঠা তেল", 2.5, "tbsp"),
                Ingredient("km4", "Crushed cumin and ginger", null, 1.5, "tbsp")
            ),
            steps = listOf(
                CookingStep(1, "Blanching Koldil", "Soak shredded banana flower in turmeric water to remove bitterness."),
                CookingStep(2, "Braising", "Brown chicken with spices, add banana flower and slow simmer covered.", timerSeconds = 1500, timerLabel = "Koldil Braise Timer")
            ),
            pairingTip = "Unforgettable when paired with fragrant Joha rice."
        ),
        Recipe(
            id = "baahor-gaj-gahori",
            title = "Baahor Gajor Logot Gahori",
            assameseTitle = "বাঁহৰ গাজৰ লগত গাহৰি মাংস • Pork with Bamboo Shoot",
            subtitle = "Mishing Tribal Feast",
            description = "Smoked pork cooked with wet-fermented bamboo shoots, fresh ginger, garlic cloves, and fiery Bhut Jolokia.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuAdRPjRbQSByITHByjiKmhzqPOi6ku0I9Kvgzsw2mPaLKukePJhLZjkctE279OEzsvRTvTpY3cQk7x33-IzLc_oGR1Q7SOgefY_E8_m337morXRUriH5hoBkWOSgbQL2WWqUMI_6Q55vOd09dWbtwOt-x6Jdr2WK-WRNmo_QGi_Czj2RgxkzdDjCXjPt7Q93pYhScc2vqBYWbfIc96emVcoqCwQJQtRgrEdjncaj3HzhvraFqRdJnrDyg",
            category = "poultry",
            categoryLabel = "Poultry & Game",
            tags = listOf("Mishing Style", "Tribal Feast", "High Heat", "Young Bamboo"),
            prepTimeMins = 15,
            cookTimeMins = 50,
            energyKcal = 490,
            tasteProfile = "Bhut Jolokia Heat",
            level = "Fiery",
            rating = 4.9,
            reviewCount = 178,
            region = "Mishing River Settlements",
            culturalNote = "Fermented young bamboo shoots (Khorisa) bring deep umami that cuts through the pork fat naturally.",
            ingredients = listOf(
                Ingredient("bg1", "Smoked or fresh pork belly", "গাহৰিৰ মাংস", 700.0, "g"),
                Ingredient("bg2", "Fermented wet bamboo shoot (Khorisa)", "বাঁহৰ গাজ / খৰিচা", 3.0, "tbsp"),
                Ingredient("bg3", "Bhut Jolokia (Ghost Pepper)", "ভোট জলকীয়া", 0.5, "pc"),
                Ingredient("bg4", "Bruised garlic & ginger", null, 2.0, "tbsp")
            ),
            steps = listOf(
                CookingStep(1, "Rendering", "Sear pork in dry wok until fat melts."),
                CookingStep(2, "Add Bamboo Shoot", "Stir in fermented bamboo shoot, crushed garlic and ghost pepper sliver."),
                CookingStep(3, "Simmer", "Add 1 cup water, cover and simmer until tender.", timerSeconds = 1800, timerLabel = "Pork Simmer Timer")
            ),
            pairingTip = "Serve with sticky rice wrapped in banana leaf."
        ),
        Recipe(
            id = "dhekia-xaak-bhaji",
            title = "Dhekia Xaak Bhaji",
            assameseTitle = "ঢেকীয়া শাকৰ ভাজি • Dhekia Bhaji",
            subtitle = "Riverbank Wild Quick & Vegan",
            description = "Tender fiddlehead greens lightly tossed in smoking mustard oil with bruised garlic cloves and green chilies.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuBG4tWGyHuOl4TJwHLwZlYAM7FipqObyhM2ig34bqTcZAnr6lWIaSWhmMKQBA-hgUJ5htWrxvnzPF3kQfgdV7XVlV2RPfA_9KmoMrwuTAxCBI8Oo6M1g27wataMGsaIVtmlXB5A-dfNiUARghzgrba3Jd5LE8p37AtekPOGVh8kxq3Pe_WoJKXErB3Dl3YM5OwKBngfJ3ZVp9yAJMyrx31pECnVzTI47jj4Zk8vqk3Hk1hafz93ZY6h5w",
            category = "greens",
            categoryLabel = "Wild Greens (Xaak)",
            tags = listOf("Riverbank Wild", "Quick & Vegan", "3 Ingredients", "Pure Green Crunch"),
            prepTimeMins = 5,
            cookTimeMins = 20,
            energyKcal = 85,
            tasteProfile = "Mild Comfort",
            level = "Vegan",
            rating = 4.7,
            reviewCount = 64,
            region = "Riverbank Wild",
            culturalNote = "Fiddlehead ferns grow along fresh alluvial riverbeds. They need fast, minimal cooking to preserve their crisp texture.",
            ingredients = listOf(
                Ingredient("dx1", "Fresh foraged Dhekia fiddlehead ferns", "ঢেকীয়া শাক", 300.0, "g"),
                Ingredient("dx2", "Cold-pressed mustard oil", "মিঠা তেল", 1.5, "tbsp"),
                Ingredient("dx3", "Bruised garlic cloves", "নহৰু", 8.0, "pods"),
                Ingredient("dx4", "Slit green chilies & pinch of salt", null, 2.0, "pcs")
            ),
            steps = listOf(
                CookingStep(1, "Heat Oil", "Heat mustard oil in cast iron pan till smoking. Toss garlic until pale golden."),
                CookingStep(2, "Stir Fry", "Add chopped tender fern fronds and green chilies. Flash sauté uncovered for 6-8 minutes.")
            ),
            pairingTip = "The perfect companion to plain white rice and dal."
        ),
        Recipe(
            id = "mati-mahor-daal-khar",
            title = "Mati Mahor Daal with Khar",
            assameseTitle = "মাটি মাহৰ খাৰ • Black Gram Khar",
            subtitle = "Alkaline Cleanse Everyday Staple",
            description = "Black lentils simmered gently with raw papaya and natural *Khar* filtered from charred dried banana peel.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCswDq7mBd59XXPwHqEcTHEr6mFJ9lEoCXTbhU0jsF4rBmvO6WJwoLxiDCOOkv1krDKj-bOrfhajWMqSBFeK62Is22Yp1Gz_K4nvLHSPadjeykV27G75RagXjaCxGh8BtLlhCGcC9SSxyOni6u4Px_DvBNjUjy0m6NbaVs1ZosCdQ0sZz6wUdIW6_xLv463Edd2nkiAh3idToKL-HioBJdOwBYixFaqrAJ42UOal4kXo7nBq-Uk5q8bcg",
            category = "khaar",
            categoryLabel = "Khaar (Alkaline)",
            tags = listOf("Alkaline Cleanse", "Everyday Staple", "Kolakhar Infusion", "Digestive Elixir"),
            prepTimeMins = 10,
            cookTimeMins = 35,
            energyKcal = 180,
            tasteProfile = "Mild Comfort",
            level = "Core Classic",
            rating = 4.8,
            reviewCount = 112,
            region = "Ancestral Hearths",
            culturalNote = "Prepared with unpolished black gram (Mati Mah). The natural banana peel ash softens the lentils without baking soda.",
            ingredients = listOf(
                Ingredient("mm1", "Whole black lentils (Mati Mah)", "মাটি মাহ", 150.0, "g"),
                Ingredient("mm2", "Raw papaya cubes", "অমিতা", 100.0, "g"),
                Ingredient("mm3", "Kolakhar extract", "কলা খাৰ", 2.0, "tbsp"),
                Ingredient("mm4", "Ginger and garlic, crushed", null, 1.5, "tbsp")
            ),
            steps = listOf(
                CookingStep(1, "Boil Lentils", "Pressure cook black lentils with water, raw papaya, and salt."),
                CookingStep(2, "Infuse Khar", "Simmer with crushed ginger and Kolakhar for 8 minutes until velvety.")
            ),
            pairingTip = "Poured over warm rice with a drop of raw mustard oil."
        )
    )

    val curatedCollections = listOf(
        CuratedCollection(
            id = "c1",
            title = "Subhasish's Childhood Favorites",
            category = "Chef's Memoir",
            recipeCount = 4,
            description = "Gentle home-cooked comfort dishes made with pure cold-pressed mustard oil and hearth smoke.",
            badge = "Slow-Simmered",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDoBAM2VRmeoa-mf3qoh0nKwe4lOZ0Mfepd-PI5jDYqUKBPZMOPrOGhaUU6XCab23tRelvWrOUpmkT06I5Ou11BlTLyU1P8BR6N-gnn17EchIQYhjzlXzoYb6qwl2WeIIvGpqlycCmFwoFmTvlHFRS0Zyw7Bcy0kDkQmORWPIKH9C2_kOfhD5hKrSYLo3sNAjOyRs8uXQZilrsR7rDNvZRdu148yV1RCkw69ou_mkEBvDJBLSEj2ix6-Q"
        ),
        CuratedCollection(
            id = "c2",
            title = "Ancestral Brahmaputra River Fish",
            category = "River Ecology",
            recipeCount = 8,
            description = "Chital muitha, steamed patot diya small fish wrapped in banana leaves, and light lemon broths.",
            badge = "Fresh Catch",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCY7ftGTHQJBchBXdwIG-mtmucYJa7Lp7QIPdIYhlBkFEpD4eqobyzylRK67chXz1PyRn-gIOMcFhcuYugsq8A9e6YmG14sWqRVOY7EIVIc-0OO-Tl5R0r6cP-qHvgB8Pvbi4c52nLb_0ruWD6KtQGT0T5JRCMrS0kY4gMxf6UHAZEBGJCvXhaP9Hkt7Ocz3wZSLnuTI9qd1M2078rphdsMeBd1ny1R11Kd6lwrpmDW1qlvYxfOOzWI9g"
        ),
        CuratedCollection(
            id = "c3",
            title = "Fermented & Foraged Greens",
            category = "Forest Foraging",
            recipeCount = 6,
            description = "Curative Dhekia fern stir-fries and aromatic Manimuni broths packed with medicinal vigor.",
            badge = "Wild Botanicals",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuD0AVTqGOaRB3kTdoJi-UCzv5pVhBN0UIFSNcJfIbweMcSl1fzllGLYIcXQZLJ4v2JnOKsfOPSpWP3dPUBJqefwYeNEV_gLeIEouIEjhcVdgUdURr8d45W0IqoN2xjh03xjvRehs6uuyuo2_HbC-4TC_aPjLjWDe-L4pnQnlIUjSaNkedsc7JB8GQEdS0sqw7syeCcmICJvWJ_6t1YstLxAf6sth4j0cddlYXN-9XuZ2bj3N0abt4sF4Q"
        )
    )

    val pantryItems = listOf(
        PantryItem(
            id = "p1",
            name = "Kaji Nemu",
            assameseName = "(Assam Lemon)",
            tag = "GI Tag",
            tagType = "Citrus",
            description = "Elongated, intensely fragrant GI-tagged citrus; floral zest essential for cooling summer dal.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDNitiM7M0FDjt6_kSOjEEkYVhU2-0nGkn-5VUMImcLz7CSOjMrn2AaRMs9Cl3DJlRl80zNQ_0PBmAuO-hVOg23kbE2LU0ygnIS0s6l9KhjynHS3AfYnXguqjYjL9NPahDe33rb9GlFaAKH4_id_YrnHJZshKAnvtEAhC5zgc6L0PdTdsw30V8HAviks0zzA_szFEkkt3CKkvtnybD8MneqzbQx_cBwwqeXIXzdHuKwh7dmiK5R6mbtpQ"
        ),
        PantryItem(
            id = "p2",
            name = "Bhut Jolokia",
            assameseName = "(Ghost Pepper)",
            tag = "Fiery",
            tagType = "Aromatic",
            description = "Smoky, intensely fiery pepper; sliced micro-thin or steeped briefly for floral, slow-blooming heat.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCjL1WlKwhtG79OO9M-5b_9EpT2SjSkzeipUpyaiUsDNUBYTrpqApTfqYIIeqY68UlYNg3WxZvmgjhO_4L6yR7fshPq7xfkr1idVW792rOYTJByD5AkydvdP0z-yDNjS5SC1zCZIHUdGxe8lRfpAVDzWhEqw6nWzSRVt5Ro4TD97tj7o_ZVcEPDttj7ZiWQvGExq3Tk32-bWh2xWuvTQr0CSz5TUjJF7Y18xhnFnWZ4UZQjqipcz32f3g"
        ),
        PantryItem(
            id = "p3",
            name = "Ou Tenga",
            assameseName = "(Elephant Apple)",
            tag = "Sour",
            tagType = "Tart Fruit",
            description = "Tangy layered sepals lightly bruised and simmered into classic fish broths and piquant chutneys.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDcUzUKC91xhipaNrzXSTaVtqYSC3XNelMwQ9EaeeZAHz2ze2A7A58WzEna221oUw7df0_B6-WDkXUq5OvyUQ_81mr0hK8Gs5tFg6ibvRn8nEBgkmba6JqbMQaDr9Ueu-bV3fVCzNWMTt5J7jkSW_dweoxGYYVZwkhpc2UoKtcoTwsY-GTSmvUBG6Hu8lLRoAuH0bBnQlBxLu-w7ubiOlXIZs9BlBvJQU7sNgBzsSb2nSmgoqnaKs3U1w"
        ),
        PantryItem(
            id = "p4",
            name = "Khar",
            assameseName = "(Kola Khar Ash Extract)",
            tag = "Elixir",
            tagType = "Digestive",
            description = "Sun-dried Bhimkol banana peel ash filtered with water; ancient alkaline palate cleanser opening every feast.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuCxKG5fYXmX_DFJ-iP395ZEMO8JFFOGtRXMZ0YtL0qxBmBJXZZp385bfh9cZhQjXH84U3ctULi6QKaVtSZCh4bZTygN8vRIdPT6MWk8KUv1U4ddfdJF3TkscrxWMKbDyQ7sFt-Vg-Twvy-_WblIJ4WOtOiryBS6RfgFlltcxXo1kgxGxgyqDKafntTiBaCXVAxvKDhhzRKzFH2CtSQ93GSIoCbLA1TjSHusWfFUoGBiASea6aiKclTSGw"
        ),
        PantryItem(
            id = "p5",
            name = "Dhekia Saak",
            assameseName = "(Fiddlehead Fern)",
            tag = "Wild Herb",
            tagType = "Foraged",
            description = "Tender coiled riverbank ferns quick-sautéed with whole chickpeas and pungent crushed mustard seeds.",
            imageUrl = "https://lh3.googleusercontent.com/aida-public/AB6AXuDDkJDiAjscpAVyrs7BF67RAIm5qX3eL9Y1M6e5O48vlFHaRxZr-7qL8srPwFehcfHuuoE3EUmwtieC3ICoB_k9-Dvj_iDuFhNG-kfiNJUKTTNn1pHCPUnXMKBeazqME87jeIpgT-cwnbPJwde88arWYGJiA1ydncwpKia902I1L6fktiFauK1orabl1q2WfC2y6Wq2-hfl_Yuwc16un_D0VG4E_knDZyHmGkTars2FyYb_fU-X6S3c9g"
        )
    )

    val culinaryPillars = listOf(
        CulinaryPillar(
            title = "Zero Waste Kitchen Tradition",
            description = "Nothing is discarded. Vegetable peels become crunchy fritters, fish bones enrich slow broths, and banana stems yield rich cooling fiber.",
            iconName = "recycling"
        ),
        CulinaryPillar(
            title = "Fermentation & Gut Biome",
            description = "From probiotic Khorisa (grated fermented bamboo shoot) to sun-cured Pani Tenga, fermentation brings complex umami and vitality.",
            iconName = "science"
        ),
        CulinaryPillar(
            title = "Cold-Pressed Mustard Oil Primacy",
            description = "Unrefined golden mustard oil is the beating pulse—cooked to slight smoke point to mellow pungency into nutty sweetness.",
            iconName = "water_drop"
        )
    )

    val chefSecrets = listOf(
        ChefSecret(
            number = 1,
            title = "Taming Paanch Phoron without Bittering",
            detail = "Warm your mustard oil until faint wisps appear, then drop the flame immediately. Introduce the five-spice blend for exactly 12 seconds until fennel turns translucent and nigella crackles. Never let the fenugreek darken into black."
        ),
        ChefSecret(
            number = 2,
            title = "Selecting Dawn River Catch at Ghat Markets",
            detail = "When seeking Rohu or Chitol at early morning river ghats, look for glistening red gills and firm elastic flesh. River fish harvested from live currents need no garlic—only turmeric, salt, and green chilies to honor their natural sweet river fat."
        )
    )

    fun getAllRecipes(): List<Recipe> {
        val all = mutableListOf(featuredMaasorTenga)
        all.addAll(trendingRecipes)
        all.addAll(exploreCatalog.filter { c -> all.none { it.id == c.id } })
        return all
    }

    fun getRecipeById(id: String): Recipe {
        return getAllRecipes().find { it.id == id } ?: featuredMaasorTenga
    }
}
