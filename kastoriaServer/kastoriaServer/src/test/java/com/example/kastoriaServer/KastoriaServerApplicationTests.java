package com.example.kastoriaServer;

import com.example.kastoriaServer.model.Vote.Vote;
import com.example.kastoriaServer.model.Vote.VoteDao;
import com.example.kastoriaServer.model.place.Place;
import com.example.kastoriaServer.model.place.PlaceDao;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class KastoriaServerApplicationTests {
	boolean hasVote=false;
	Vote previousVote;
	@Autowired
	private PlaceDao placeDao;
	@Autowired
	private VoteDao votedao;
//
//	@BeforeAll
//	public  void clear() {
//		List<Place> places = placeDao.getAllPlaces();
//		for (Place place : places) {		placeDao.delete(place.getId());
//		}
//	}

	@Test
	void addPlaceTest() {
		placeDao.deleteAll();
		votedao.deleteAll();
//		insertDao("Europahotel","https://www.europahotelkastoria.gr/images/pages/page1-img1.jpg",4.2f,40.521720703862485f, 21.264272168957074f,"Hotel",0f,"Ξενοδοχείο , στο κέντρο της πόλης της Καστοριάς","https://www.europahotelkastoria.gr/el/");
//		insertDao("Allahou","https://www.allahou.gr/images/yootheme/home_slide_01a.jpg",4.8f,40.521465223697966f, 21.266702348745078f,"Hotel",0f,"Μια πρώην κατοικία νεοκλασσικού στυλ στην Καστοριά, προσφέρει υψηλού επιπέδου φιλοξενία στον επισκέπτη.","https://www.allahou.gr/index.php/el/");
		insertDao("Europahotel","https://www.europahotelkastoria.gr/images/pages/page1-img1.jpg",0f,40.521720703862485f, 21.264272168957074f,"Hotel",0f,"Ξενοδοχείο , στο κέντρο της πόλης της Καστοριάς","https://www.europahotelkastoria.gr/el/");
		insertDao("Allahou","https://www.allahou.gr/images/yootheme/home_slide_01a.jpg",0f,40.521465223697966f, 21.266702348745078f,"Hotel",0f,"Μια πρώην κατοικία νεοκλασσικού στυλ στην Καστοριά, προσφέρει υψηλού επιπέδου φιλοξενία στον επισκέπτη.","https://www.allahou.gr/index.php/el/");
		insertDao("Hotel Enastron View ","https://www.hotelenastron.gr/photos/365.jpg",0f,40.55143206874627f, 21.251436231490455f,"Hotel",0f,"Φιλόξενο κατάλυμα με θέα στο βουνό","http://www.hotelenastron.gr/");
		insertDao("Hotel Doltso","https://assets.hotelwize.com/site-media/45/img_6766-a-1.jpg?_=3a797ac1&format=jpg&width=1920&height=1080",0f,40.516455f,21.271833f,"Hotel",0f,"Γραφικό ξενοδοχείο σε πρώην αρχοντικό","https://www.doltsohotel.gr/");
		insertDao("Hotel Kastoria","https://www.hotel-kastoria.gr/images/phocagallery/hotelkastoria/thumbs/phoca_thumb_l_5.jpg",0f,40.521925f,21.273909f,"Hotel",0f,"Χαλαρό ξενοδοχείο με καφέ/μπαρ","http://www.lake.gr/");
		insertDao("Παλιά Πόλη","https://5d13b8ffa7.cbaul-cdnwnd.com/b842cecc403b45cd137df819ad458173/200000074-b2c93b2c95-public/paliapolifota.jpg",0f,40.51552735224801f, 21.271769782450342f,"Rest",0f,"Ένα ζεστό παραδοσιακό περιβάλλον με πολλές παλιές καστοριανές συνταγές","https://paliapoli.webnode.gr/");
		insertDao("Κατσαρόλα","https://lh3.googleusercontent.com/p/AF1QipN3n8ZHLzWgdq49JeYvZSEL8IWG0m_9Q8PFCVQ=w1080-h608-p-no-v0",0f,40.522591f,21.264773f,"Rest",0f,"Παραδοσιακό εστιατόριο","https://restaurant-50540.business.site/");
		insertDao("Η Πάπια","https://lh5.googleusercontent.com/p/AF1QipPwXqyWT0MqMutyvyvNmWnEB5hiV5fR5hSq3OW9=w425-h240-k-no",0f,40.525493f,21.263521f,"Rest",0f,"Πίτσα","http://www.papia.gr/");
		insertDao("Εν Καιρώ","https://lh5.googleusercontent.com/p/AF1QipO5t4Fsvx2XzjWocYzj0tbfdIwIhWV5noGs87G0=w426-h240-k-no",0f,40.515261f,21.265964f,"Rest",0f,"Μικρά πιάτα","https://kafepoteionenkairw.business.site/");
		insertDao("Basilico ","https://www.basilico.gr/wp-content/uploads/2020/03/Basilico_060.jpg",0f,40.523088f,21.264177f,"Rest",0f,"Ιταλική κουζίνα","https://www.basilico.gr/index.php/reservationsapp/");
		insertDao("Λαογραφικό Μουσείο","https://lh5.googleusercontent.com/p/AF1QipPpziL7U6StUG5bYUTImx-0DD_D92cjd53eEnme=w408-h306-k-no",0f,40.516385f,21.273546f,"Sights",0f,"Λαογραφικό Μουσείο","https://www.digitalkastoria.gr/el/culture-tourism-events-el/museums-visiting-spaces-el/traditional-mansions-el/arxontiko-neratzi-aivazi-el");
		insertDao("Βυζαντινό Μουσείο","https://www.bmk.gr/wp-content/uploads/2016/02/arxiki-selida.jpg",0f,40.519117f,21.268479f,"Sights",0f,"Βυζαντινό Μουσείο","https://www.bmk.gr/");
		insertDao("Ενυδρείο Καστοριάς","https://lh5.googleusercontent.com/p/AF1QipPz99lgzrtVLKIhHYfYyCJCpV7UqbSXkXoeNqzB=w408-h245-k-no",0f,40.512575f,21.253382f,"Sights",0f,"Ενυδρείο","http://enydriokastorias.gr/");
		insertDao("Σπήλαιο του Δράκου","https://lh5.googleusercontent.com/p/AF1QipM-6RNHRQscBn7dnMiIkvV1P4eDtWAA-lSmMVol=w408-h544-k-no",0f,40.505122f,21.283633f,"Sights",0f,"Σπήλαιο","https://www.spilaiodrakoukast.gr/");
		insertDao("Βυζαντινά Τείχη","https://lh5.googleusercontent.com/p/AF1QipNJC02pUBa3buHpJArZvt-5rF7DRMmLivztOJbz=w408-h544-k-no",0f,40.522765f,21.264623f,"Sights",0f,"Βυζαντινά Τείχη","https://www.kastra.eu/castlegr.php?kastro=kastoria");
		insertDao("Prague Live Stage","https://lh5.googleusercontent.com/p/AF1QipPRWgkgnQ12-lpq_ZIO5n3ipgk5YPTE_TrHrVZ1=w427-h240-k-no",0f,40.519723f,21.257487f,"Entert",0f,"Μπαρ με ζωντανή σκηνή","https://prague-kastoria.business.site/");
		insertDao("ΟΛΥΜΠΙΟΝ ","https://lh5.googleusercontent.com/p/AF1QipM7xn02e0fEEWPyHrxoHlSpRqLsTXb8CJJewJr3=w426-h240-k-no",0f,40.519790f,21.266250f,"Entert",0f,"Κινηματογράφος","https://olympionhouse.gr/");
		insertDao("BACCARA","https://scontent.fskg4-1.fna.fbcdn.net/v/t1.18169-9/1833_40041929726_3110_n.jpg?_nc_cat=106&ccb=1-7&_nc_sid=825194&_nc_ohc=TZD5vITXU4wAX_ZSaUQ&_nc_ht=scontent.fskg4-1.fna&oh=00_AT_TZUUZXz5_coMf1M6K-E_Qyt6UQiFjB1gE5gY5ao9kOQ&oe=633D9166",4.4f,40.518626f,21.264865f,"Entert",0f,"Καφετέρια","https://www.facebook.com/groups/40629107874/");
		insertDao("THE PASSENGER","https://lh5.googleusercontent.com/p/AF1QipOlFTv6p-DYijjPTErnjmHgMpqokzVWjqopWcv9=w426-h240-k-no",0f,40.520158f,21.263012f,"Entert",0f,"Εσπρέσο μπαρ","https://m.facebook.com/ThePassengerKastoria?_rdr");
		insertDao("Περίπου Cocktail Bar","https://lh5.googleusercontent.com/p/AF1QipNWT_T-m0L96aKXTvanGKgGOpNMNQ75_BtRrdQ3=w408-h408-k-no",0f,40.515713f,21.273972f,"Entert",0f,"Cocktail Bar","https://peripou.business.site/");
	}

	private void insertDao( String name,
			String imageUrl,
			Float rating,
			Float lat,
			Float lon,
			String category,
			Float distance,
			String description,
			String placeUrl){
		Place place = new Place();
		place.setName(name);
		place.setImageUrl(imageUrl);
		place.setRating(rating);
		place.setLat(lat);
		place.setLon(lon);
		place.setCategory(category);
		place.setDistance(distance);
		place.setDescription(description);
		place.setPlaceUrl(placeUrl);
		placeDao.save(place);
	}
	private void insert (Place place){
		placeDao.save(place);
	}
/*
	private void addVote(int placeid,String email,Float rating){
		Place place;
		place=placeDao.findPlaceByid(placeid);
		List <Vote>curentVoteList=place.getVotes();
		for (Vote vote:curentVoteList) {
			if (email.equals(vote.getEmail())){
				hasVote=true;
				previousVote=vote;
				break;

			}
		}
		if (hasVote==false){
			Vote vote=new Vote();
			vote.setPlace(place);
			vote.setEmail(email);
			vote.setRating(rating);
			place.setRating(rating);
			votedao.save(vote);
			place.setRating(rating(placeid));
			placeDao.save(place);
		}
		if (hasVote){
			previousVote.setRating(rating);
			votedao.save(previousVote);
			place.setRating(rating(placeid));
			placeDao.save(place);
			hasVote=false;
		}

	}

 */
/*
	private float rating(int placeid){
		Place place =placeDao.findPlaceByid(placeid);
		float rating=0f;
		List<Vote> votelist=  place.getVotes();
		for (Vote vote:votelist) {
			rating +=vote.getRating();

		}
		if (votelist.size()!=0)
			rating=(rating/votelist.size());
		return rating;
	}
*/


	@Test
	void contextLoads() {
	}

}
