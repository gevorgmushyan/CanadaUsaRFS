package policy;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

@Data
public class Quote implements Serializable {

    public static final List<String> annualCoverages = List.of(
            "Unlimited", "$25,000", "$20,000", "$15,000", "$10,000", "$5,000", "$2,500"
    );

    public static List<String> deductibles = List.of(
            "$250", "$300", "$350", "$400", "$450", "$500", "$550", "$600", "$650", "$700", "$750", "$800", "$850",
            "$900", "$950", "$1,000", "$1,100", "$1,200", "$1,300", "$1,400", "$1,500", "$1,600", "$1,700", "$1,800",
            "$1,900", "$2,000", "$2,100", "$2,200", "$2,300", "$2,400", "$2,500"
    );

    public static List<String> reimbursements = List.of(
            "70%", "80%", "90%"
    );

    public static List<String> spiPureDogBreeds = List.of("Affenpinscher", "Afghan Hound", "Aidi", "Ainu Dog", "Airedale Terrier", "Akbash Dog", "Akita", "Alano Español", "Alapaha Blue Blood Bulldog", "Alaskan Husky", "Alaskan Klee Kai", "Alaskan Malamute", "Alpine Dachsbracke", "Alsatian", "American Bulldog", "American Bullnese", "American Bully", "American Cocker Spaniel", "American Eskimo Dog", "American Eskimo Dog - miniature", "American Foxhound", "American Hairless Terrier", "American Indian Dog", "American Lo-Sze Pugg", "American Mastiff", "American Pit Bull Terrier", "American Staffordshire Terrier", "American Staghound", "American Tundra Shepherd", "American Water Spaniel", "American White Shepherd", "Anatolian Shepherd Dog", "Appenzell Mountain Dog", "Argentine Dogo", "Ariegoeis", "Australian Cattle Dog", "Australian Kelpie", "Australian Shepherd", "Australian Shepherd - miniature", "Australian Terrier", "Austrian Brandlbracke", "Austrian Shorthaired Pinscher", "Azawakh", "Barbet", "Basenji", "Basset Artesien Normand", "Basset Bleu De Gascogne", "Basset Fauve De Bretagne", "Basset Hound", "Beagle", "Bearded Collie", "Beauceron", "Bedlington Terrier", "Belgian Shepherd Groenendael", "Belgian Shepherd Laekenois", "Belgian Shepherd Malinois", "Belgian Shepherd Tervuren", "Bergamasco", "Berger Blanc Swiss", "Berger des Pyrenees", "Berger Picard", "Bernese Mountain Dog", "Bichon Frise", "Biewer", "Black Mouth Cur", "Black Russian Terrier", "Bloodhound", "Blue Heeler", "Blue Lacy", "Bluetick Coonhound", "Boerboel", "Bolognese", "Bolonka", "Border Collie", "Border Terrier", "Borzoi", "Bosnian Coarse Haired Hound", "Boston Terrier", "Bouvier Des Flandres", "Boxer", "Boykin Spaniel", "Bracco Italiano", "Braque d Auvergne", "Braque du Bourbonnais", "Braque Français", "Brazilian Fila", "Brazilian Terrier", "Briard", "Briquet Griffon Vendeen", "Brittany", "Brussels Griffon", "Bull Terrier", "Bull Terrier - miniature", "Bulldog", "Bullmastiff", "Ca de Bou", "Cairn Terrier", "Canaan Dog", "Canadian Eskimo Dog", "Canary Dog", "Cane Corso", "Cão de  Fila de São Miguel", "Cardigan Welsh Corgi", "Carolina Dog", "Catalan Sheepdogs", "Catahoula Leopard Dog", "Caucasian Ovtcharka", "Cavalier King Charles Spaniel", "Central Asian Ovtcharka", "Cesky Fousek", "Cesky Terrier", "Chasa Apso", "Chesapeake Bay Retriever", "Chien D' Artois", "Chihuahua", "Chihuahua long-haired", "Chinese Chongqing Dog", "Chinese Crested Dog", "Chinese Crested Hairless", "Chinese Crested Powder Puff", "Chinese Foo", "Chinese Imperial", "Chinook", "Chow Chow", "Cirneco dell'Etna", "Clumber Spaniel", "Cocker Spaniel", "Cocker Spaniel Miniature", "Collie", "Collie smooth-coated", "Coonhound - black and tan", "Coonhound - blue tick", "Coonhound - red bone", "Corgi", "Coton De Tulear", "Curly-Coated Retriever", "Czechoslovakian Wolfdog (Vlcák)", "Dachshund", "Dachshund - long haired", "Dachshund - smooth coat", "Dachshund - wire haired", "Dachshund miniature - long haired", "Dachshund miniature - smooth coat", "Dachshund miniature - wire haired", "Dalmatian", "Dandie Dinmont Terrier", "Danish-Swedish Farmdog", "Deerhound", "Deutsch Drahthaar", "Deutscher Wachtelhund", "Doberman Pinscher", "Dogo Argentino", "Dogue De Bordeaux", "Drever", "Dunker", "Dutch Sheepdog", "Dutch Shepherd", "Dutch Smoushond", "Elkhound", "English Bulldog", "English Cocker Spaniel", "English Coonhound", "English Foxhound", "English Mastiff", "English Pointer", "English Setter", "English Shepherd", "English Springer Spaniel", "English Toy Spaniel", "English Toy Terrier", "Entlebucher Mountain Dog", "Eskimo Dog", "Estonian Hound", "Estrela Mountain Dog", "Eurasier", "Fell Terrier", "Field Spaniel", "Fila Brasileiro", "Finnish Hound", "Finnish Lapphund", "Finnish Spitz", "Flat-coated Retriever", "Formosan Mountain Dog", "Fox Hound", "Fox Terrier", "French Bulldog", "French Mastiff", "French Spaniel", "Gazelle Hound", "German Longhaired Pointer", "German Pinscher", "German Shepherd", "German Shorthaired Pointer", "German Spitz", "German Wirehaired Pointer", "Giant Schnauzer", "Glen Of Imaal Terrier", "Golden Retriever", "Gordon Setter", "Gran Mastin de Borinquen", "Grand Basset Griffon Vendeen", "Grand Bleu de Gascogne", "Great Dane", "Great Pyrenean", "Great Pyrenees", "Greater Swiss Mountain Dog", "Greenland Dog", "Greyhound", "Griffon Bruxellois", "Groenendael", "Hamiltonstovare", "Harrier", "Havanese", "Hokkaido", "Hovawart", "Hungarian Kuvasz", "Hungarian Puli", "Hungarian Pumi", "Hungarian Sheepdog", "Hungarian Vizsla", "Hungarian Wire Haired Vizsla", "Ibizan Hound", "Icelandic Sheepdog", "Inca Hairless Dog", "Inca Orchid", "Indian Pariah Dog", "Irish Red and White Setter", "Irish Setter", "Irish Staffordshire Bull Terrier", "Irish Terrier", "Irish Water Spaniel", "Irish Wolfhound", "Istrian Shorthaired Hound", "Italian Greyhound", "Jack Russell Terrier", "Japanese Chin", "Japanese Spitz", "Japanese Terrier", "Jindo", "Kai Dog", "Kangal Dog", "Karabash", "Karelian Bear Dog", "Keeshond", "Kelpie", "Kerry Blue Terrier", "King Charles Spaniel", "King Shepherd", "Kishu Inu", "Komondor", "Kooikerhondje", "Koolie", "Korean Jindo", "Kuvasz", "Kyi-Leo", "Labrador Retriever", "Labrador Retriever - Black", "Labrador Retriever - Chocolate", "Labrador Retriever - Yellow", "Laekenois", "Lagotto Romagnolo", "Lakeland Terrier", "Lancashire Heeler", "Landseer", "Lapinporokoira", "Large Münsterländer", "Leonberger", "Lhasa Apso", "Llewellin Setter", "Louisiana Catahoula Leopard Dog", "Löwchen", "Lucas Terrier", "Lurcher", "Malamute Husky", "Malinois", "Maltese", "Manchester Terrier", "Maremma Sheepdog", "Mastiff", "Mexican Hairless Crested", "Mexican Hairless Dog", "Miki", "Miniature Dachshund", "Miniature Pinscher", "Miniature Poodle", "Miniature Schnauzer", "Mioritic Sheepdog", "Mountain Cur", "Mountain Feist", "Mudi", "Native American Indian Dog", "Neapolitan Mastiff", "Nebolish Mastiff", "New Zealand Huntaway (Sheepdog)", "Newfoundland", "Nippon Terrier", "Norfolk Terrier", "Northern Inuit Dog", "Norwegian Buhund", "Norwegian Elkhound", "Norwegian Lundehund", "Norwich Terrier", "Nova Scotia Duck Tolling Retriever", "Old English Mastiff", "Old English Sheepdog", "Olde English Bulldogge", "Olde Victorian Bulldogge", "Ori Pei", "Otterhound", "Papillon", "Parson Jack Russell Terrier", "Patterdale Terrier", "Pekingese", "Pembroke Welsh Corgi", "Perro de Presa Mallorquin", "Peruvian Inca Orchid", "Petit Basset Griffon Vendeen", "Petit Brabançon", "Pharaoh Hound", "Pit Bull", "Plott Hound", "Pointer", "Polish Lowland Sheepdog", "Polish Tatra Sheepdog", "Pomeranian", "Poodle", "Poodle - Giant", "Poodle - Miniature", "Poodle - Standard", "Poodle - Teacup", "Poodle - Toy", "Porcelaine", "Portuguese Mastiff (Rafeiro Do Alentejo)", "Portuguese Podengo", "Portuguese Pointer", "Portuguese Water Dog", "Presa Canario", "Pudelpointer", "Pug", "Puli", "Pumi", "Pyrenean Mastiff", "Pyrenean Mountain Dog", "Pyrenean Shepherd", "Queensland Heeler", "Rat Terrier", "Rhodesian Ridgeback", "Rottweiler", "Rough Collie", "Saarlooswolfhond", "Saint Bernard", "Saluki", "Samoyed", "Sarplaninac", "Schapendoes", "Schillerstövare", "Schiller Hound", "Schipperke", "Schnauzer", "Schnauzer - giant", "Schnauzer - miniature", "Schnauzer - standard", "Scottish Deerhound", "Scottish Terrier", "Sealyham Terrier", "Segugio Italiano", "Shar-pei", "Shetland Sheepdog", "Shiba Inu", "Shih Tzu", "Shikoku", "Shiloh Shepherd", "Siberian Husky", "Silken Windhound", "Silky Terrier", "Skye Terrier", "Sloughi", "Small Münsterländer", "Smooth Collie", "Smooth Fox Terrier", "Soft Coated Wheaten Terrier", "Spanish Bulldog", "Spanish Mastiff", "Spanish Water Dog", "Spinone italiano", "Spitz", "Stabyhoun (Stabij)", "Staffordshire Bull Terrier", "Staghound", "Sussex Spaniel", "Swedish Elkhound", "Swedish Lapphund", "Swedish Vallhund", "Taiwanese Native Dog", "Taskasago Dog", "Tervuren", "Thai Ridgeback", "Tibetan KyiApso", "Tibetan Mastiff", "Tibetan Spaniel", "Tibetan Terrier", "Tornjak", "Tosa Inu", "Toy Fox Terrier", "Toy Terrier", "Transylvanian Hound", "Treeing Tennessee Brindle", "Treeing Walker Coonhound", "Victorian Bulldog", "Vizsla", "Volpino Italiano", "Weimaraner", "Welsh Corgi", "Welsh Corgi - Cardigan", "Welsh Corgi - Pembroke", "Welsh Springer Spaniel", "Welsh Terrier", "West Highland White Terrier", "Wheaten Terrier", "White Swiss Shepherd", "Whippet", "Wirehaired Fox Terrier", "Wirehaired Pointing Griffon", "Wirehaired Vizsla", "Xoloitzcuintli - Mexican Hairless Dog", "Yorkie-Poo", "Yorkshire Terrier");
    public static List<String> spiMixedDogBreeds = List.of("Airedale Mix", "Akita Mix", "American Eskimo Mix", "American Staffordshire Terrier Mix", "Appenzeller Sennenhund", "Australian Cattle Dog Mix", "Australian Shepherd Mix", "Basset Hound Mix", "Beagle Mix", "Belgian Shepherd Mix", "Bernese Mountain Dog Mix", "Bichon Frise Mix", "Border Collie Mix", "Boston Terrier Mix", "Bouvier Des Flandres Mix", "Boxer Mix", "Brittany Mix", "Brussels Griffon Mix", "BullBoxer", "Bulldog Mix", "Bullmastiff Mix", "Cairn Terrier Mix", "Cavachon", "Cavalier King Charles Spaniel Mix", "Chihuahua Mix", "Chow Chow Mix", "Cockapoo", "Cocker Spaniel Mix", "Collie Mix", "Corgi Mix", "Coton De Tulear Mix", "Dachshund  Mix", "Dalmatian Mix", "Doberman Pinscher Mix", "Dorgie", "English Cocker Spaniel Mix", "English Mastiff Mix", "English Setter Mix", "English Springer Spaniel Mix", "French Bulldog Mix", "German Shepherd Mix", "German Shorthaired Pointer Mix", "Golden Labrador (hybrid)", "Goldendoodle", "Great Dane Mix", "Great Pyrenees Mix", "Greater Swiss Mountain Dog Mix", "Greyhound Mix", "Havanese Mix", "Irish Setter Mix", "Italian Greyhound Mix", "Jack Russell Terrier Mix", "Japanese Chin Mix", "Labradoodle", "Labrador Husky", "Labrador Mix", "Lhasa Apso Mix", "Malamute Mix", "Malt-A-Poo", "Maltese Mix", "Mastiff Mix", "Mini Goldendoodle", "Miniature Pinscher Mix", "Mixed Breed Small ( up to 22lb )", "Mixed Breed Medium (23 - 70lb)", "Mixed Breed Large (71lb +)", "Newfoundland Mix", "Old English Sheepdog Mix", "Papillon Mix", "Peek-a-poo", "Pekingese Mix", "Pit Bull Mix", "Pomeranian Mix", "Poodle Mix", "Portuguese Water Dog Mix", "Pug Mix", "Puggle", "Retriever Mix", "Rhodesian Ridgeback Mix", "Rottweiler Mix", "Saint Bernard mix", "Samoyed Mix", "Schnauzer Mix", "Schnoodle", "Shar-pei Mix", "Shetland Sheepdog Mix", "Shiba Inu Mix", "Shih Tzu Mix", "Shiloh Shepherd Mix", "Siberian Husky Mix", "Spaniel Mix", "Staffordshire Bull Terrier Mix", "Terrier Mix", "Vizsla Mix", "Weimaraner Mix", "Wheaten Terrier Mix", "Whippet Mix", "Yorkshire Terrier Mix");

    public static List<String> spiPureCatBreeds = List.of("Abyssinian", "American Bobtail", "American Curl", "American Curl Shorthair", "American Keuda", "American Longhair", "American Shorthair (purebred)", "American Wirehair", "Angora", "Australian Mist", "Balinese", "Bengal", "Bengalese", "Birman", "Bombay", "Brazilian Shorthair", "British Shorthair", "Burmese", "Burmilla", "California Spangled Cat", "Cameo Longhair", "Chantilly/Tiffany", "Chartreux", "Chausie", "Chinchilla", "Colorpoint", "Colorpoint Longhair", "Colorpoint Shorthair", "Cornish Rex", "Cymric", "Devon Rex", "Donskoy", "Egyptian Mau", "European Burmese", "European Shorthair", "Exotic", "Exotic Longhair", "Exotic Shorthair", "Feral", "German Rex", "Havana Brown", "Highland Fold", "Highland Lynx", "Himalayan", "Himalayan Blue Point", "Himalayan Flame Point", "Himalayan Lilac Point", "Himalayan Seal Point", "Japanese Bobtail", "Javanese", "Kashmir", "Korat", "La Perm", "Lykoi", "Maine Coon", "Manx", "Manx Longhair", "Munchkin", "Nebelung", "Nibelung", "Norwegian Forest Cat", "Ocicat", "Oriental", "Oriental Colorpoint", "Oriental Longhair", "Oriental Shorthair", "Persian", "Peterbald", "Pinto", "Pixie-Bob", "RagaMuffin", "Ragdoll", "Russian Blue", "Safari Cat", "Savannah", "Scotish Fold", "Scottish Fold Longhair", "Selkirk Rex", "Selkirk Rex Longhair", "Servel", "Seychellois", "Siamese", "Siberian", "Singapura", "Snowshoe", "Sokoke", "Somali", "Sphynx", "Sterling", "Tiffanie", "Tonkinese", "Turkish Angora", "Turkish Van", "York Chocolate");
    public static List<String> spiMixedCatBreeds = List.of("Domestic Longhair", "Domestic Mediumhair", "Domestic Shorthair", "Mixed Breed", "Moggie");

    public static List<String> sfDogBreeds = List.of("Mixed Breed Small (up to 22lb when full grown)", "Mixed Breed Medium (23 - 70lb when full grown)", "Mixed Breed Large (71lb + when full grown)", "Affenpinscher", "Afghan Hound", "Aidi", "Ainu Dog", "Airedale Terrier", "Akbash Dog", "Akita", "Alano Espanol", "Alapaha Blue Blood Bulldog", "Alaskan Husky", "Alaskan Klee Kai", "Alaskan Malamute", "Alpine Dachsbracke", "Alsatian", "American Bulldog", "American Bullnese", "American Cocker Spaniel", "American Eskimo Dog", "American Eskimo Dog - miniature", "American Foxhound", "American Hairless Terrier", "American Indian Dog", "American Lo-Sze Pugg", "American Mastiff", "American Pit Bull Terrier", "American Staffordshire Terrier", "American Staghound", "American Tundra Shepherd", "American Water Spaniel", "American White Shepherd", "Anatolian Shepherd Dog", "Appenzell Mountain Dog", "Argentine Dogo", "Ariegoeis", "Australian Cattle Dog", "Australian Kelpie", "Australian Shepherd", "Australian Shepherd - miniature", "Australian Terrier", "Austrian Brandlbracke", "Austrian Shorthaired Pinscher", "Azawakh", "Barbet", "Basenji", "Basset Artesien Normand", "Basset Bleu De Gascogne", "Basset Fauve De Bretagne", "Basset Hound", "Beagle", "Bearded Collie", "Beauceron", "Bedlington Terrier", "Belgian Shepherd Groenendael", "Belgian Shepherd Laekenois", "Belgian Shepherd Malinois", "Belgian Shepherd Tervuren", "Bergamasco", "Berger des Pyrenees", "Berger Picard", "Bernese Mountain Dog", "Bichon Frise", "Biewer", "Black Mouth Cur", "Black Russian Terrier", "Bloodhound", "Blue Heeler", "Blue Lacy", "Bluetick Coonhound", "Boerboel", "Bolognese", "Bolonka", "Border Collie", "Border Terrier", "Borzoi", "Bosnian Coarse Haired Hound", "Boston Terrier", "Bouvier Des Flandres", "Boxer", "Boykin Spaniel", "Bracco Italiano", "Braque d Auvergne", "Braque du Bourbonnais", "Braque Francais", "Brazilian Fila", "Brazilian Terrier", "Briard", "Briquet Griffon Vendeen", "Brittany", "Brussels Griffon", "Bull Terrier", "Bull Terrier - miniature", "BullBoxer", "Bulldog", "Bullmastiff", "Ca de Bou", "Cairn Terrier", "Canaan Dog", "Canadian Eskimo Dog", "Canary Dog", "Cane Corso", "Cao de Fila de Sao Miguel", "Cardigan Welsh Corgi", "Carolina Dog", "Catahoula Leopard Dog", "Caucasian Ovtcharka", "Cavachon", "Cavalier King Charles Spaniel", "Central Asian Ovtcharka", "Cesky Fousek", "Cesky Terrier", "Chasa Apso", "Chesapeake Bay Retriever", "Chien D Artois", "Chihuahua", "Chihuahua long-haired", "Chinese Chongqing Dog", "Chinese Crested Dog", "Chinese Crested Hairless", "Chinese Crested Powder Puff", "Chinese Foo", "Chinese Imperial", "Chinook", "Chow Chow", "Cirneco dell Etna", "Clumber Spaniel", "Cockapoo", "Cocker Spaniel", "Cocker Spaniel Miniature", "Collie", "Collie smooth-coated", "Coonhound - black and tan", "Coonhound - blue tick", "Coonhound - red bone", "Corgi", "Coton De Tulear", "Curly-Coated Retriever", "Czechoslovakian Wolfdog (Vlcak)", "Dachshund", "Dachshund - long haired", "Dachshund - smooth coat", "Dachshund - wire haired", "Dachshund miniature - long haired", "Dachshund miniature - smooth coat", "Dachshund miniature - wire haired", "Dalmatian", "Dandie Dinmont Terrier", "Danish-Swedish Farmdog", "Deerhound", "Deutsch Drahthaar", "Deutscher Wachtelhund", "Doberman Pinscher", "Dogo Argentino", "Dogue De Bordeaux", "Dorgie", "Drever", "Dunker", "Dutch Sheepdog", "Dutch Shepherd", "Dutch Smoushond", "Elkhound", "English Bulldog", "English Cocker Spaniel", "English Coonhound", "English Foxhound", "English Mastiff", "English Pointer", "English Setter", "English Shepherd", "English Springer Spaniel", "English Toy Spaniel", "English Toy Terrier", "Entlebucher Mountain Dog", "Eskimo Dog", "Estonian Hound", "Estrela Mountain Dog", "Eurasier", "Fell Terrier", "Field Spaniel", "Fila Brasileiro", "Finnish Hound", "Finnish Lapphund", "Finnish Spitz", "Flat-coated Retriever", "Fox Hound", "Fox Terrier", "French Bulldog", "French Mastiff", "French Spaniel", "Gazelle Hound", "German Longhaired Pointer", "German Pinscher", "German Shepherd", "German Shorthaired Pointer", "German Spitz", "German Wirehaired Pointer", "Giant Schnauzer", "Glen Of Imaal Terrier", "Golden Labrador (hybrid)", "Golden Retriever", "Goldendoodle", "Gordon Setter", "Gran Mastin de Borinquen", "Grand Basset Griffon Vendeen", "Grand Bleu de Gascogne", "Great Dane", "Great Pyrenean", "Great Pyrenees", "Greater Swiss Mountain Dog", "Greenland Dog", "Greyhound", "Griffon Bruxellois", "Groenendael", "Hamiltonstovare", "Harrier", "Havanese", "Hovawart", "Hungarian Kuvasz", "Hungarian Puli", "Hungarian Pumi", "Hungarian Sheepdog", "Hungarian Vizsla", "Hungarian Wire Haired Vizsla", "Ibizan Hound", "Icelandic Sheepdog", "Inca Hairless Dog", "Inca Orchid", "Irish Red and White Setter", "Irish Setter", "Irish Staffordshire Bull Terrier", "Irish Terrier", "Irish Water Spaniel", "Irish Wolfhound", "Italian Greyhound", "Jack Russell Terrier", "Japanese Chin", "Japanese Spitz", "Japanese Terrier", "Kai Dog", "Kangal Dog", "Karabash", "Karelian Bear Dog", "Keeshond", "Kelpie", "Kerry Blue Terrier", "King Charles Spaniel", "King Shepherd", "Kishu Inu", "Komondor", "Kooikerhondje", "Koolie", "Korean Jindo", "Kuvasz", "Kyi-Leo", "Labradoodle", "Labrador Husky", "Labrador Retriever", "Labrador Retriever - Black", "Labrador Retriever - Chocolate", "Labrador Retriever - Yellow", "Laekenois", "Lagotto Romagnolo", "Lakeland Terrier", "Lancashire Heeler", "Landseer", "Lapinporokoira", "Large Munsterlander", "Leonberger", "Lhasa Apso", "Llewellin Setter", "Louisiana Catahoula Leopard Dog", "Lowchen", "Lucas Terrier", "Lurcher", "Malamute Husky", "Malinois", "Malt-A-Poo", "Maltese", "Manchester Terrier", "Maremma Sheepdog", "Mastiff", "Mexican Hairless Crested", "Mexican Hairless Dog", "Miki", "Miniature Dachshund", "Miniature Pinscher", "Miniature Poodle", "Miniature Schnauzer", "Mioritic Sheepdog", "Mountain Cur", "Mountain Feist", "Mudi", "Native American Indian Dog", "Neapolitan Mastiff", "Nebolish Mastiff", "New Zealand Huntaway (Sheepdog)", "Newfoundland", "Nippon Terrier", "Norfolk Terrier", "Northern Inuit Dog", "Norwegian Buhund", "Norwegian Elkhound", "Norwegian Lundehund", "Norwich Terrier", "Nova Scotia Duck Tolling Retriever", "Old English Mastiff", "Old English Sheepdog", "Olde English Bulldogge", "Olde Victorian Bulldogge", "Ori Pei", "Otterhound", "Papillon", "Parson Jack Russell Terrier", "Patterdale Terrier", "Peek-a-poo", "Pekingese", "Pembroke Welsh Corgi", "Perro de Presa Mallorquin", "Peruvian Inca Orchid", "Petit Basset Griffon Vendeen", "Petit Brabancon", "Pharaoh Hound", "Pit Bull", "Plott Hound", "Pointer", "Polish Lowland Sheepdog", "Polish Tatra Sheepdog", "Pomeranian", "Poodle", "Poodle - Giant", "Poodle - Miniature", "Poodle - Standard", "Poodle - Teacup", "Poodle - Toy", "Porcelaine", "Portuguese Mastiff (Rafeiro Do Alentejo)", "Portuguese Podengo", "Portuguese Pointer", "Portuguese Water Dog", "Presa Canario", "Pudelpointer", "Pug", "Puggle", "Puli", "Pumi", "Pyrenean Mastiff", "Pyrenean Mountain Dog", "Pyrenean Shepherd", "Queensland Heeler", "Rat Terrier", "Rhodesian Ridgeback", "Rottweiler", "Rough Collie", "Saarlooswolfhond", "Saint Bernard", "Saluki", "Samoyed", "Sarplaninac", "Schapendoes", "Schipperke", "Schnauzer", "Schnauzer - giant", "Schnauzer - miniature", "Schnauzer - standard", "Schnoodle", "Scottish Deerhound", "Scottish Terrier", "Sealyham Terrier", "Segugio Italiano", "Shar-pei", "Shetland Sheepdog", "Shiba Inu", "Shih Tzu", "Shikoku", "Shiloh Shepherd", "Siberian Husky", "Silken Windhound", "Silky Terrier", "Skye Terrier", "Sloughi", "Small Munsterlander", "Smooth Collie", "Smooth Fox Terrier", "Soft Coated Wheaten Terrier", "Spanish Bulldog", "Spanish Mastiff", "Spanish Water Dog", "Spinone italiano", "Spitz", "Stabyhoun (Stabij)", "Staffordshire Bull Terrier", "Staghound", "Sussex Spaniel", "Swedish Elkhound", "Swedish Lapphund", "Swedish Vallhund", "Tervuren", "Thai Ridgeback", "Tibetan KyiApso", "Tibetan Mastiff", "Tibetan Spaniel", "Tibetan Terrier", "Tosa Inu", "Toy Fox Terrier", "Toy Terrier", "Treeing Tennessee Brindle", "Treeing Walker Coonhound", "Victorian Bulldog", "Vizsla", "Volpino Italiano", "Weimaraner", "Welsh Corgi", "Welsh Corgi - Cardigan", "Welsh Corgi - Pembroke", "Welsh Springer Spaniel", "Welsh Terrier", "West Highland White Terrier", "Wheaten Terrier", "Whippet", "Wirehaired Fox Terrier", "Wirehaired Pointing Griffon", "Wirehaired Vizsla", "Xoloitzcuintli - Mexican Hairless Dog", "Yorkie-Poo", "Yorkshire Terrier");
    public static List<String> sfCatBreeds = List.of("Domestic Shorthair", "Domestic Longhair", "Domestic Mediumhair", "Abyssinian", "American Bobtail", "American Curl", "American Curl Shorthair", "American Keuda", "American Longhair", "American Shorthair (purebred)", "American Wirehair", "Angora", "Australian Mist", "Balinese", "Bengal", "Bengalese", "Birman", "Bombay", "Brazilian Shorthair", "British Shorthair", "Burmese", "Burmilla", "California Spangled Cat", "Cameo Longhair", "Chantilly/Tiffany", "Chartreux", "Chausie", "Chinchilla", "Colorpoint", "Colorpoint Longhair", "Colorpoint Shorthair", "Cornish Rex", "Cymric", "Devon Rex", "Egyptian Mau", "European Burmese", "European Shorthair", "Exotic", "Exotic Longhair", "Exotic Shorthair", "Feral", "German Rex", "Havana Brown", "Highland Fold", "Highland Lynx", "Himalayan", "Himalayan Blue Point", "Himalayan Flame Point", "Himalayan Lilac Point", "Himalayan Seal Point", "Japanese Bobtail", "Javanese", "Kashmir", "Korat", "La Perm", "Maine Coon", "Manx", "Manx Longhair", "Mixed Breed", "Munchkin", "Nebelung", "Nibelung", "Norwegian Forest Cat", "Ocicat", "Oriental", "Oriental Colorpoint", "Oriental Longhair", "Oriental Shorthair", "Persian", "Peterbald", "Pinto", "Pixie-Bob", "RagaMuffin", "Ragdoll", "Russian Blue", "Safari Cat", "Savannah", "Scotish Fold", "Scottish Fold Longhair", "Selkirk Rex", "Selkirk Rex Longhair", "Seychellois", "Siamese", "Siberian", "Singapura", "Snowshoe", "Sokoke", "Somali", "Sphynx", "Sterling", "Tiffanie", "Tonkinese", "Turkish Angora", "Turkish Van", "York Chocolate");

    private String name;
    private String type;
    private String breed;
    private String breedId;
    private boolean isPure;
    private String age;
    private String annualCoverage;
    private String annualDeductible;
    private String reimbursement;
    private String premium;
    private String payPeriod;
    private String billingType;
    private String effectiveDate;
    private String status;
    private boolean isWorkingDog;
    private String policyNumber;
    private String ipid;
    private String pdid;

    public Quote() {
        setName("My pet");
        setType("dog");
        setBreed("Affenpinscher");
        setBreedId("3JQT5U07");
        setAge("1 year");
        setAnnualCoverage("15000");
        setAnnualDeductible("300");
        setReimbursement("80");
        setPayPeriod("Monthly");
        setBillingType("Monthly"); //???
        //setEffectiveDate(DateHelper.getNextDay("yyyy-MM-dd"));
        setIsPure(true);
        setWorkingDog(false);
    }

    public void setRandomSchemeForSpi() {
        setAnnualCoverage(annualCoverages.get(new Random().nextInt(annualCoverages.size())));
        setAnnualDeductible(deductibles.get(new Random().nextInt(deductibles.size())));
        setReimbursement(reimbursements.get(new Random().nextInt(reimbursements.size())));
    }

    public void setRandomSchemeForSF() {
        if (Double.parseDouble(age.trim()) >= 10) {
            setAnnualCoverage("$15,000");
            setAnnualDeductible("$750");
            setReimbursement("70%");
        } else {
            setAnnualCoverage(annualCoverages.get(new Random().nextInt(annualCoverages.size())));
            setAnnualDeductible(deductibles.get(new Random().nextInt(15)));
            setReimbursement(reimbursements.get(new Random().nextInt(reimbursements.size())));
        }
    }

    public void setRandomSchemeForSPI() {
        setAnnualCoverage(annualCoverages.get(new Random().nextInt(annualCoverages.size())));
        setAnnualDeductible(deductibles.get(new Random().nextInt(deductibles.size())));
        setReimbursement(reimbursements.get(new Random().nextInt(reimbursements.size())));
    }

    public void setHigherRiskSchemeForSPI() {
        setRandomSchemeForSPI();
        int i = new Random().nextInt(3);
        switch (i) {
            case 0:
                setAnnualCoverage(annualCoverages.get(new Random().nextInt(3)));
                break;
            case 1:
                setAnnualDeductible(deductibles.get(new Random().nextInt(10)));
                break;
            case 2:
                setReimbursement(reimbursements.get(new Random().nextInt(reimbursements.size() - 1) + 1));
                break;
        }
    }

    public void setLowerRiskSchemeForSPI() {
        setRandomSchemeForSPI();
        setAnnualCoverage(annualCoverages.get(new Random().nextInt(annualCoverages.size() - 3) + 3));
        setAnnualDeductible(deductibles.get(new Random().nextInt(deductibles.size() - 10) + 10));
        setReimbursement(reimbursements.get(0));
    }

    public void setExactPlanForSPI(String annualCoverage, String deductible, String reimbursement) {
        if(!annualCoverages.contains(annualCoverage))
            throw new IllegalArgumentException("Illegal argument annualCoverage: " + annualCoverage);
        if(!deductibles.contains(deductible))
            throw new IllegalArgumentException("Illegal argument deductibles: " + deductible);
        if(!reimbursements.contains(reimbursement))
            throw new IllegalArgumentException("Illegal argument reimbursements: " + reimbursement);

        setAnnualCoverage(annualCoverage);
        setAnnualDeductible(deductible);
        setReimbursement(reimbursement);
    }

    private void setRandomBreedForSpiAndSf(String petType, List<String> spiBreedList, List<String> sfBreedList) {
        if (petType.toLowerCase().trim().equals("dog"))
            setType("dog");
        else if (petType.toLowerCase().trim().equals("cat"))
            setType("cat");
        else
            throw new IllegalArgumentException("Illegal petType argument");
        while (true) {
            int i = new Random().nextInt(Math.min(spiBreedList.size(), sfBreedList.size()));
            String breed = spiBreedList.get(i);
            if (sfBreedList.contains(breed)) {
                setBreed(breed);
                return;
            }
        }
    }

    public void setRandomPureBreedForDogForSpiAndSf() {
        setRandomBreedForSpiAndSf("dog", spiPureDogBreeds, sfDogBreeds);
        setIsPure(true);
    }

    public void setRandomPureBreedForCatForSpiAndSf() {
        setRandomBreedForSpiAndSf("cat", spiPureCatBreeds, sfCatBreeds);
        setIsPure(true);
    }

    public void setRandomMixedBreedForDogForSpiAndSf() {
        setRandomBreedForSpiAndSf("dog", spiMixedDogBreeds, sfDogBreeds);
        setIsPure(false);
    }

    public void setRandomMixedBreedForCatForSpiAndSf() {
        setRandomBreedForSpiAndSf("cat", spiMixedCatBreeds, sfCatBreeds);
        setIsPure(false);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public boolean getIsPure() {
        return isPure;
    }

    public void setIsPure(boolean isPure) {
        this.isPure = isPure;
    }

    public String getBreedId() {
        return breedId;
    }

    public void setBreedId(String breedId) {
        this.breedId = breedId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAnnualCoverage() {
        return annualCoverage;
    }

    public void setAnnualCoverage(String annualCoverage) {
        this.annualCoverage = annualCoverage;
    }

    public String getAnnualDeductible() {
        return annualDeductible;
    }

    public void setAnnualDeductible(String annualDeductible) {
        this.annualDeductible = annualDeductible;
    }

    public String getReimbursement() {
        return reimbursement;
    }

    public void setReimbursement(String reimbursement) {
        this.reimbursement = reimbursement;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getBillingType() {
        return billingType;
    }

    public void setBillingType(String billingType) {
        this.billingType = billingType;
    }

    public boolean isQuoteActive() {
        if (status == null)
            return false;
        if (status.equals("1") || status.equals("2"))
            return true;
        return false;
    }
}
