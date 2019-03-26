# ΠΧ7. Παρουσίαση Δαπανών Τελευταίου Επταημέρου

**Πρωτεύων Actor**: Ημερολόγιο Εφαρμογής  
**Ενδιαφερόμενοι**  
**Χρήστης**: Θέλει να ενημερώνεται αυτόματα για τα έξοδα της περασμένης εβδομάδας.  
**Προϋποθέσεις**: Ο χρήστης έχει επιλέξει να λαμβάνει ειδοποιήσεις από την εφαρμογή και έχει καθορίσει τη μέρα της εβδομάδας από την οποία ξεκινά η μέτρηση του ημερολογίου.

## Βασική Ροή
1. Το ημερολόγιο ενεργοποιείται μετά το πέρασμα της 7ης ημέρας.
2. Το ημερολόγιο ζητά την ανάκτηση των εξόδων του τελευταίου επταημέρου.
3. Το σύστημα υπολογίζει τη συνολική αξία των επισκέψεων που συνέβησαν στο ζητούμενο χρονικό διάστημα.
4. Το ημερολόγιο προωθεί το αποτέλεσμα στο χρήστη μέσω ειδοποίησης εφαρμογής και επανεκκινεί τη μέτρηση.

## Εναλλακτικές Ροές
--