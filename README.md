# TourismAgencySystem
 
Tourism Agency System Patika Tourism Agency needs a structure in which it can run its business digitally. Assume that you have agreed with Patika for this digital infrastructure.

The working logic of Patika Tourism Agency is as follows: Hotels reserve certain rooms with Patika Tourism Agency and expect the agency to sell these rooms. When selling these rooms, the agency sells it to its customers by putting its own service fee as a percentage or a fixed price.

The agency thinks that doing these works on paper and manually is a waste of time, and needs a software that will make their job easier.

Expectation of the agency from the software:

Hotel management The agency should manage the hotels it has contracted with, together with their location information and other features, from the system. While adding a hotel, other definitions such as Hotel Name, Address, E-mail, Telephone, Star, Facility Features, Hostel types are made.

Hostel Types expected to be in the system: Ultra All Inclusive All inclusive Room breakfast Full pension Half board Bed Only Full credit without alcohol Facility Features expected to be in the system: Free Parking Free WiFi Swimming pool Fitness Center Hotel Concierge SPA 24/7 Room Service Sample Hotel Data;

Term Management Historical periods of the hotels are added and pricing is made over these periods. The aim here is to offer a variable pricing. While hotel prices are higher in summer, this is less in winter. Pricing is made periodically in the tourism sector. Periods are defined as two date ranges.

Example Periods:

01/01/2023 - 31/05/2023 01/06/2023 - 01/12/2023 Room Management The agency adds the rooms reserved from the hotels to the system and provides pricing on these rooms. Generally Single, Double, Suit etc. in hotels. There are certain room types, such as . It should also be entered in the properties of the rooms.

Room Features expected to be in the system: Other information other than the number of beds is optional.

Number of Beds Television (Yes, No) Minibar (Yes, No) Game Console (Yes, No) Square meters Till Projection Sample Room Data;

Room Pricing Rooms are calculated as per night rate. The prices are defined according to the hostel and adult child types over the periods added for the hotel.

Pricing expected in the system: Prices are defined according to the nightly rates of the rooms. These prices include the agency's commission fee.

Room Search and Reservation Procedures The agency employee should be able to search for a room according to the date range, Region or Hotels and guest information entered through the system.

Room Search Algorithm After the agency enters the required room search information, it can list the rooms defined in the system.

For rooms to appear in the list:

The hotel of the room must be in the desired region. According to the desired date range, the hotel should have period information. For example, for a search with Check-in Date: 09/06/2023 , Check-out Date: 12/06/2023, hotels must have a period in that date range. If there is a period for the hotel, the room should also have price information according to the hostel types in the relevant periods so that price information can be given to the customer. The stock number of the room must be greater than 0. Example Search Data Region : Beyoglu Entry Date: 01/04/2023 Release Date: 03/04/2023 Guest Information: 2 Adults, 1 Child Price Calculation Prices are calculated based on the guest information, the number of nights to stay and the hostel.

According to this information

First, the hotels in the region are found. There are rooms with stock belonging to the hotel found. If the rooms have period price information in the relevant date range, the price calculation is made.

Reservation Process The agency user completes the reservation by selecting the appropriate hostel type from the room types of the hotels he has listed.

To complete the reservation:

Customer contact information Guest Name Surname and ID information and completes the sale through the system. If the sale is completed, the stock of the relevant room will be reduced by 1.

Reservation List Agency employees can list the reservations made on the system
